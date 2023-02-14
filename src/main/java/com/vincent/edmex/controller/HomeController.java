package com.vincent.edmex.controller;

import com.vincent.edmex.pojo.Message;
import com.vincent.edmex.pojo.User;
import com.vincent.edmex.service.MessageService;
import com.vincent.edmex.service.RoleService;
import com.vincent.edmex.service.RoomService;
import com.vincent.edmex.service.UserService;
import com.vincent.edmex.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    RoomService roomService;

    @Autowired
    DateUtils dateUtils;

    //TODO: inviare messaggio in caso si cerchi di inviare un messaggio prima di x tempo
    @GetMapping(value = {"/home", "/", ""})
    public ModelAndView home(Model model, Authentication authentication) {

        User loggedUser = (User) authentication.getPrincipal();
        model.addAttribute("message", Message.builder().message("").sender("").build());
        logger.info("GET : " + model.getAttribute("message").toString());

        if (roleService.isAdmin(((User) authentication.getPrincipal()).getRole())) {
            model.addAttribute("roomIds", roomService.getAllRoomId());
        }

        Message lastMessage = messageService.getLastMessage(loggedUser.getIdRoom(), roleService.isAdmin(loggedUser.getRole()));

        String dateFormatted = dateUtils.isToday(lastMessage.getCreatedDate()) ? "OGGI ALLE " + dateUtils.formatHours().format(lastMessage.getCreatedDate()) : dateUtils.formatData().format(lastMessage.getCreatedDate());

        model.addAttribute("lastMessageDate", dateFormatted);
        model.addAttribute("lastMessageSender",
                "INVIATO DA : " + lastMessage.getSender().toUpperCase());

        return new ModelAndView("home");
    }

    /*TODO:
     - Vedere se è il caso di aggiungere una nuova riga per messaggio(e quindi aggiungere un batch
       che pulisca quelli più vecchi di X tempo) oppure fare update
     - Inviare messaggio in caso si cerchi di inviare un messaggio prima di x tempo
       se l'utente è ROLE_USER prendere il numero di stanza,
       se ROLE_ADMIN creare un menu per decidere quale messaggio prendere
    */
    @PostMapping()
    public ModelAndView setMessage(@ModelAttribute Message message, Model model, Authentication authentication) {
        boolean isAdmin = roleService.isAdmin(((User) authentication.getPrincipal()).getRole());

        User loggedUser = (User) authentication.getPrincipal();
        logger.info("POST : " + model.getAttribute("message").toString());
        if (isAdmin) {
            model.addAttribute("roomIds", roomService.getAllRoomId());
        }
        model.addAttribute("message", message);
        if (!isAdmin) {
            message.setIdRoom(loggedUser.getIdRoom());
        }
        Message messageToUpdate =
                Message.builder()
                        .idRoom(message.getIdRoom())
                        .sender(message.getSender())
                        //stripAccents permette di convertire lettere accentate in normali
                        //per evitare errori di visualizzazione sull'lcd
                        .message(StringUtils.stripAccents(message.getMessage()))
                        .createdDate(new Date())
                        .build();

        messageService.setMessage(messageToUpdate);

        return new ModelAndView("home");
    }


    @GetMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView mav = new ModelAndView("/login");
        //setto il viewName altrimenti avviando il jar non riconosce il login
        mav.setViewName("login");
        return mav;
    }
}

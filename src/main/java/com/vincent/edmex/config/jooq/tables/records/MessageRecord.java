/*
 * This file is generated by jOOQ.
 */
package com.vincent.edmex.config.jooq.tables.records;


import com.vincent.edmex.config.jooq.tables.Message;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MessageRecord extends UpdatableRecordImpl<MessageRecord> implements Record4<Integer, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ed_mex.message.idRoom</code>.
     */
    public void setIdroom(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>ed_mex.message.idRoom</code>.
     */
    public Integer getIdroom() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>ed_mex.message.sender</code>.
     */
    public void setSender(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>ed_mex.message.sender</code>.
     */
    public String getSender() {
        return (String) get(1);
    }

    /**
     * Setter for <code>ed_mex.message.message</code>.
     */
    public void setMessage(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>ed_mex.message.message</code>.
     */
    public String getMessage() {
        return (String) get(2);
    }

    /**
     * Setter for <code>ed_mex.message.created_date</code>.
     */
    public void setCreatedDate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>ed_mex.message.created_date</code>.
     */
    public LocalDateTime getCreatedDate() {
        return (LocalDateTime) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, LocalDateTime> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Message.MESSAGE.IDROOM;
    }

    @Override
    public Field<String> field2() {
        return Message.MESSAGE.SENDER;
    }

    @Override
    public Field<String> field3() {
        return Message.MESSAGE.MESSAGE_;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Message.MESSAGE.CREATED_DATE;
    }

    @Override
    public Integer component1() {
        return getIdroom();
    }

    @Override
    public String component2() {
        return getSender();
    }

    @Override
    public String component3() {
        return getMessage();
    }

    @Override
    public LocalDateTime component4() {
        return getCreatedDate();
    }

    @Override
    public Integer value1() {
        return getIdroom();
    }

    @Override
    public String value2() {
        return getSender();
    }

    @Override
    public String value3() {
        return getMessage();
    }

    @Override
    public LocalDateTime value4() {
        return getCreatedDate();
    }

    @Override
    public MessageRecord value1(Integer value) {
        setIdroom(value);
        return this;
    }

    @Override
    public MessageRecord value2(String value) {
        setSender(value);
        return this;
    }

    @Override
    public MessageRecord value3(String value) {
        setMessage(value);
        return this;
    }

    @Override
    public MessageRecord value4(LocalDateTime value) {
        setCreatedDate(value);
        return this;
    }

    @Override
    public MessageRecord values(Integer value1, String value2, String value3, LocalDateTime value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MessageRecord
     */
    public MessageRecord() {
        super(Message.MESSAGE);
    }

    /**
     * Create a detached, initialised MessageRecord
     */
    public MessageRecord(Integer idroom, String sender, String message, LocalDateTime createdDate) {
        super(Message.MESSAGE);

        setIdroom(idroom);
        setSender(sender);
        setMessage(message);
        setCreatedDate(createdDate);
    }
}

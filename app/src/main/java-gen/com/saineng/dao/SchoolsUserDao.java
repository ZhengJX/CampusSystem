package com.saineng.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.SchoolsUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SCHOOLS_USER.
*/
public class SchoolsUserDao extends AbstractDao<SchoolsUser, Long> {

    public static final String TABLENAME = "SCHOOLS_USER";

    /**
     * Properties of entity SchoolsUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property No_pass_add_time = new Property(1, String.class, "no_pass_add_time", false, "NO_PASS_ADD_TIME");
        public final static Property Daily_use_amount = new Property(2, String.class, "daily_use_amount", false, "DAILY_USE_AMOUNT");
        public final static Property Daily_use_num = new Property(3, String.class, "daily_use_num", false, "DAILY_USE_NUM");
        public final static Property Is_bind_parents = new Property(4, String.class, "is_bind_parents", false, "IS_BIND_PARENTS");
        public final static Property New_card_type = new Property(5, String.class, "new_card_type", false, "NEW_CARD_TYPE");
        public final static Property Card_sn = new Property(6, String.class, "card_sn", false, "CARD_SN");
        public final static Property Token = new Property(7, String.class, "token", false, "TOKEN");
        public final static Property Openid = new Property(8, String.class, "openid", false, "OPENID");
        public final static Property Agent_token = new Property(9, String.class, "agent_token", false, "AGENT_TOKEN");
        public final static Property Agent_openid = new Property(10, String.class, "agent_openid", false, "AGENT_OPENID");
        public final static Property User_type = new Property(11, String.class, "user_type", false, "USER_TYPE");
        public final static Property Group_id = new Property(12, String.class, "group_id", false, "GROUP_ID");
        public final static Property User_level = new Property(13, String.class, "user_level", false, "USER_LEVEL");
        public final static Property Group_id_admin = new Property(14, String.class, "group_id_admin", false, "GROUP_ID_ADMIN");
        public final static Property Name = new Property(15, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(16, String.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(17, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Phone = new Property(18, String.class, "phone", false, "PHONE");
        public final static Property Verify_code = new Property(19, String.class, "verify_code", false, "VERIFY_CODE");
        public final static Property Is_verified = new Property(20, String.class, "is_verified", false, "IS_VERIFIED");
        public final static Property Verify_time = new Property(21, String.class, "verify_time", false, "VERIFY_TIME");
        public final static Property Account_deposit = new Property(22, String.class, "account_deposit", false, "ACCOUNT_DEPOSIT");
        public final static Property Subject = new Property(23, String.class, "subject", false, "SUBJECT");
        public final static Property Is_subscribe = new Property(24, String.class, "is_subscribe", false, "IS_SUBSCRIBE");
        public final static Property Subscribe_openid = new Property(25, String.class, "subscribe_openid", false, "SUBSCRIBE_OPENID");
        public final static Property Origin_card_sn = new Property(26, String.class, "origin_card_sn", false, "ORIGIN_CARD_SN");
        public final static Property UnionId = new Property(27, String.class, "unionId", false, "UNION_ID");
        public final static Property App_openid = new Property(28, String.class, "app_openid", false, "APP_OPENID");
    };


    public SchoolsUserDao(DaoConfig config) {
        super(config);
    }
    
    public SchoolsUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SCHOOLS_USER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NO_PASS_ADD_TIME' TEXT," + // 1: no_pass_add_time
                "'DAILY_USE_AMOUNT' TEXT," + // 2: daily_use_amount
                "'DAILY_USE_NUM' TEXT," + // 3: daily_use_num
                "'IS_BIND_PARENTS' TEXT," + // 4: is_bind_parents
                "'NEW_CARD_TYPE' TEXT," + // 5: new_card_type
                "'CARD_SN' TEXT," + // 6: card_sn
                "'TOKEN' TEXT," + // 7: token
                "'OPENID' TEXT," + // 8: openid
                "'AGENT_TOKEN' TEXT," + // 9: agent_token
                "'AGENT_OPENID' TEXT," + // 10: agent_openid
                "'USER_TYPE' TEXT," + // 11: user_type
                "'GROUP_ID' TEXT," + // 12: group_id
                "'USER_LEVEL' TEXT," + // 13: user_level
                "'GROUP_ID_ADMIN' TEXT," + // 14: group_id_admin
                "'NAME' TEXT," + // 15: name
                "'SEX' TEXT," + // 16: sex
                "'BIRTHDAY' TEXT," + // 17: birthday
                "'PHONE' TEXT," + // 18: phone
                "'VERIFY_CODE' TEXT," + // 19: verify_code
                "'IS_VERIFIED' TEXT," + // 20: is_verified
                "'VERIFY_TIME' TEXT," + // 21: verify_time
                "'ACCOUNT_DEPOSIT' TEXT," + // 22: account_deposit
                "'SUBJECT' TEXT," + // 23: subject
                "'IS_SUBSCRIBE' TEXT," + // 24: is_subscribe
                "'SUBSCRIBE_OPENID' TEXT," + // 25: subscribe_openid
                "'ORIGIN_CARD_SN' TEXT," + // 26: origin_card_sn
                "'UNION_ID' TEXT," + // 27: unionId
                "'APP_OPENID' TEXT);"); // 28: app_openid
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SCHOOLS_USER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SchoolsUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String no_pass_add_time = entity.getNo_pass_add_time();
        if (no_pass_add_time != null) {
            stmt.bindString(2, no_pass_add_time);
        }
 
        String daily_use_amount = entity.getDaily_use_amount();
        if (daily_use_amount != null) {
            stmt.bindString(3, daily_use_amount);
        }
 
        String daily_use_num = entity.getDaily_use_num();
        if (daily_use_num != null) {
            stmt.bindString(4, daily_use_num);
        }
 
        String is_bind_parents = entity.getIs_bind_parents();
        if (is_bind_parents != null) {
            stmt.bindString(5, is_bind_parents);
        }
 
        String new_card_type = entity.getNew_card_type();
        if (new_card_type != null) {
            stmt.bindString(6, new_card_type);
        }
 
        String card_sn = entity.getCard_sn();
        if (card_sn != null) {
            stmt.bindString(7, card_sn);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(8, token);
        }
 
        String openid = entity.getOpenid();
        if (openid != null) {
            stmt.bindString(9, openid);
        }
 
        String agent_token = entity.getAgent_token();
        if (agent_token != null) {
            stmt.bindString(10, agent_token);
        }
 
        String agent_openid = entity.getAgent_openid();
        if (agent_openid != null) {
            stmt.bindString(11, agent_openid);
        }
 
        String user_type = entity.getUser_type();
        if (user_type != null) {
            stmt.bindString(12, user_type);
        }
 
        String group_id = entity.getGroup_id();
        if (group_id != null) {
            stmt.bindString(13, group_id);
        }
 
        String user_level = entity.getUser_level();
        if (user_level != null) {
            stmt.bindString(14, user_level);
        }
 
        String group_id_admin = entity.getGroup_id_admin();
        if (group_id_admin != null) {
            stmt.bindString(15, group_id_admin);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(16, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(17, sex);
        }
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(18, birthday);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(19, phone);
        }
 
        String verify_code = entity.getVerify_code();
        if (verify_code != null) {
            stmt.bindString(20, verify_code);
        }
 
        String is_verified = entity.getIs_verified();
        if (is_verified != null) {
            stmt.bindString(21, is_verified);
        }
 
        String verify_time = entity.getVerify_time();
        if (verify_time != null) {
            stmt.bindString(22, verify_time);
        }
 
        String account_deposit = entity.getAccount_deposit();
        if (account_deposit != null) {
            stmt.bindString(23, account_deposit);
        }
 
        String subject = entity.getSubject();
        if (subject != null) {
            stmt.bindString(24, subject);
        }
 
        String is_subscribe = entity.getIs_subscribe();
        if (is_subscribe != null) {
            stmt.bindString(25, is_subscribe);
        }
 
        String subscribe_openid = entity.getSubscribe_openid();
        if (subscribe_openid != null) {
            stmt.bindString(26, subscribe_openid);
        }
 
        String origin_card_sn = entity.getOrigin_card_sn();
        if (origin_card_sn != null) {
            stmt.bindString(27, origin_card_sn);
        }
 
        String unionId = entity.getUnionId();
        if (unionId != null) {
            stmt.bindString(28, unionId);
        }
 
        String app_openid = entity.getApp_openid();
        if (app_openid != null) {
            stmt.bindString(29, app_openid);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SchoolsUser readEntity(Cursor cursor, int offset) {
        SchoolsUser entity = new SchoolsUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // no_pass_add_time
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // daily_use_amount
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // daily_use_num
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // is_bind_parents
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // new_card_type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // card_sn
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // token
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // openid
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // agent_token
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // agent_openid
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // user_type
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // group_id
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // user_level
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // group_id_admin
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // name
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // sex
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // birthday
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // phone
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // verify_code
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // is_verified
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // verify_time
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // account_deposit
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // subject
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // is_subscribe
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // subscribe_openid
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // origin_card_sn
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // unionId
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28) // app_openid
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SchoolsUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNo_pass_add_time(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDaily_use_amount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDaily_use_num(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIs_bind_parents(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNew_card_type(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCard_sn(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setToken(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setOpenid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAgent_token(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAgent_openid(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setUser_type(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setGroup_id(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUser_level(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setGroup_id_admin(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setName(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setSex(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setBirthday(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setPhone(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setVerify_code(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setIs_verified(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setVerify_time(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setAccount_deposit(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setSubject(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setIs_subscribe(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setSubscribe_openid(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setOrigin_card_sn(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setUnionId(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setApp_openid(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SchoolsUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SchoolsUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
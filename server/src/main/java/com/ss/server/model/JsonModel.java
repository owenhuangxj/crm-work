package com.ss.server.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonModel extends HashMap<String, Object> implements Serializable {
    /////////////////////// 默认的键 ///////////////////////

    public static final String KEY_OPER = "oper";
    public static final String KEY_SUCC = "success";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG = "msg";
    public static final String KEY_DATA = "data";

    /////////////////////// 默认的值 ///////////////////////

    public static final String DEFAULT_OPER_VAL = "default";
    public static final int DEFAULT_SUCC_CODE = 1;
    public static final int DEFAULT_FAIL_CODE = -1;
    public static final String DEFAULT_SUCC_MSG = "ok";
    public static final String DEFAULT_FAIL_MSG = "fail";


    /////////////////////// 最通用的两个构造函数 ///////////////////////

    /**
     * 无参构造：操作成功返回的响应信息
     */
    public JsonModel() {
        this.put(KEY_OPER, DEFAULT_OPER_VAL);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 操作成功返回的响应信息
     */
    public JsonModel(String oper) {
        this.put(KEY_OPER, oper);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 全参构造方法
     */
    public JsonModel(String operate, boolean success, int code, String msg, Object data) {
        this.put(KEY_OPER, operate);
        this.put(KEY_SUCC, success);
        this.put(KEY_CODE, code);
        this.put(KEY_MSG, msg);
        if (data != null) {
            this.put(KEY_DATA, data);
        }
    }

    /////////////////////// 各种简便的静态工厂方法 ///////////////////////
    public JsonModel(int initialCapacity) {
        super(initialCapacity);
    }

    public JsonModel(Map<? extends String, ?> m) {
        super(m);
    }

    public JsonModel(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    ////// 操作成功的返回方法：
    public static JsonModel succ() {
        return new JsonModel();
    }

    public static JsonModel succ(String operate) {
        return new JsonModel(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null);
    }


    public static JsonModel succ(String operate, Object data) {
        return new JsonModel(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, data);
    }

    public static JsonModel succ(String operate, String dataKey, Object dataVal) {
        return new JsonModel(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null).setReturnData(dataKey, dataVal);
    }

    ////// 操作失败的返回方法：
    public static JsonModel fail() {
        return new JsonModel(DEFAULT_OPER_VAL, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    public static JsonModel fail(String operate) {
        return new JsonModel(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }


    public static JsonModel fail(String operate, String message) {
        return new JsonModel(operate, false, DEFAULT_FAIL_CODE, message, null);
    }

    public static JsonModel fail(String operate, Object data) {
        return new JsonModel(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, data);
    }

    public static JsonModel fail(String operate, String dataKey, Object dataVal) {
        return new JsonModel(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null).setReturnData(dataKey, dataVal);
    }

    ////// 操作动态判定成功或失败的：
    public static JsonModel succ(String operate, String message) {
        return new JsonModel(operate, true, DEFAULT_SUCC_CODE, message, null);
    }

    /////////////////////// 各种链式调用方法 ///////////////////////

    public static JsonModel result(String operate, boolean success) {
        return new JsonModel(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                null
        );
    }

    public static JsonModel result(String operate, boolean success, Object data) {
        return new JsonModel(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                data);
    }

    public static JsonModel result(String operate, boolean success, String dataKey, Object dataVal) {
        return new JsonModel(
                operate,
                success,
                (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE),
                (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG),
                null
        ).setReturnData(dataKey, dataVal);
    }

    public static String getKeyOper() {
        return KEY_OPER;
    }

    public static String getKeySucc() {
        return KEY_SUCC;
    }

    public static String getKeyCode() {
        return KEY_CODE;
    }

    public static String getKeyMsg() {
        return KEY_MSG;
    }

    public static String getKeyData() {
        return KEY_DATA;
    }

    public static String getDefaultOperVal() {
        return DEFAULT_OPER_VAL;
    }

    public static int getDefaultSuccCode() {
        return DEFAULT_SUCC_CODE;
    }

    public static int getDefaultFailCode() {
        return DEFAULT_FAIL_CODE;
    }

    public static String getDefaultSuccMsg() {
        return DEFAULT_SUCC_MSG;
    }

    public static String getDefaultFailMsg() {
        return DEFAULT_FAIL_MSG;
    }

    /**
     * 设置操作名称
     */
    public JsonModel oper(String operate) {
        this.put(KEY_OPER, operate);
        return this;
    }

    /**
     * 设置操作结果是否成功的标记
     */
    public JsonModel succ(boolean success) {
        this.put(KEY_SUCC, success);
        return this;
    }

    /**
     * 设置操作结果的代码
     */
    public JsonModel code(int code) {
        this.put(KEY_CODE, code);
        return this;
    }

    /**
     * 设置操作结果的信息
     */
    public JsonModel msg(String message) {
        this.put(KEY_MSG, message);
        return this;
    }

    /**
     * 设置操作返回的数据
     */
    public JsonModel setReturnData(Object dataVal) {
        this.put(KEY_DATA, dataVal);
        return this;
    }

    /**
     * 设置操作返回的数据，数据使用自定义的key存储
     */
    public JsonModel setReturnData(String dataKey, Object dataVal) {
        this.put(dataKey, dataVal);
        return this;
    }
}

package team.xuli.toe.util;

/**
 * @author: 徐清锋
 * 创建时间：2016/3/4
 * 创建原因：
 */
public class AppConst {
    //Admin account
    public static String ADMIN_ACCOUNT = "admin";
    public static int ADMIN_ID = 1;
    //ROLE CONST
    public static String ROLE_SYS_ADMIN = "ROLE_SYS_ADMIN";
    public static String ROLE_OWNER = "ROLE_OWNER";
    public static String ROLE_DELIVERER = "ROLE_DELIVERER";

    //Order status
    public static  int ORDER_STATUS_DELETED = 10;//不能为-1
    public static  int ORDER_STATUS_NEW = 0;
    public static  int ORDER_STATUS_ASSIGNED = 1;
    public static  int ORDER_STATUS_COMPLETED = 2;

    //Address status
    public static int ADDRESS_STATUS_ENABLED = 1;
    public static int ADDRESS_STATUS_DISABLED = 0;

    //NONE_SENSE CONST
    public static String ROLE_NONE_SENSE = "NO_ROLE";
    public static int STATUS_NONE_SENSE = -1;
    public static int MONEY_NONE_SENSE = -1;
    public static int CREDIT_NONE_SENSE = -1;
    public static int ID_NONE_SENSE = 0;
    public static Object OBJECT_NONE_SENSE = null;

    //INIT CONST
    public static int INIT_CREDIT = 0;
    public static int INIT_MONEY = 0;

    //ADDRESS_TYPE CONST
    public static int ADDRESS_TYPE_ORG = 0;
    public static int ADDRESS_TYPE_DEST = 1;

    //Credit increment
    public static int CREDIT_INCREMENT_FOR_DELIVERER = 1;
    public static int CREDIT_INCREMENT_FOR_OWNER = 1;
}

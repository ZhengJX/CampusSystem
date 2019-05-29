package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        //初始化一下数据库
        //第一个参数是版本号,第二个参数是为你生成的bean类放的文件夹名
        Schema schema = new Schema(23, "greendao");
        //设置一下生成的三个java文件的目录
        schema.setDefaultJavaPackageDao("com.saineng.dao");

        //建立一个表
        Entity userBean = schema.addEntity("Users");
        //此行可有可无,就是对表进行重命名
        userBean.setTableName("Users");
        //此行被注,因为加上引行,下面的字段总会少一个.不知道为什么
        //userBean.addLongProperty("id").primaryKey().index().autoincrement();
        //建立自增的主键
        userBean.addIdProperty();
        userBean.addStringProperty("order_id");
        userBean.addStringProperty("goods_name");
        userBean.addStringProperty("goods_num");
        userBean.addStringProperty("user_name");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("user_phone");
        userBean.addStringProperty("valid_start");
        userBean.addStringProperty("valid_end");
        userBean.addBooleanProperty("is_receive");
        userBean.addStringProperty("group_name");
        userBean.addStringProperty("user_type");
        userBean.addStringProperty("dish_date");

        Entity infoTypeBean = schema.addEntity("Orders");
        infoTypeBean.addIdProperty();
        infoTypeBean.addStringProperty("card_id");
        infoTypeBean.addStringProperty("money");

        Entity foodBean = schema.addEntity("FoodBean");
        foodBean.addIdProperty();
        foodBean.addStringProperty("f_order_id");
        foodBean.addStringProperty("f_goods_name");
        foodBean.addStringProperty("f_goods_num");
        foodBean.addStringProperty("f_user_name");
        foodBean.addStringProperty("f_user_id");
        foodBean.addStringProperty("f_user_phone");
        foodBean.addStringProperty("f_valid_start");
        foodBean.addStringProperty("f_valid_end");
        foodBean.addStringProperty("group_name");
        foodBean.addStringProperty("user_type");
        foodBean.addStringProperty("dish_date");

        Entity cardBean = schema.addEntity("CardNum");
        cardBean.addIdProperty();
        cardBean.addStringProperty("card_sn");

        Entity schoolBean = schema.addEntity("Schools");
        schoolBean.addIdProperty();
        schoolBean.addStringProperty("token");
        schoolBean.addStringProperty("room_id");
        schoolBean.addStringProperty("desc");

        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");

    }
}

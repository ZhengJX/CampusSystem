package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table FOOD_BEAN.
 */
public class FoodBean {

    private Long id;
    private String f_order_id;
    private String f_goods_name;
    private String f_goods_num;
    private String f_user_name;
    private String f_user_id;
    private String f_user_phone;
    private String f_valid_start;
    private String f_valid_end;
    private String group_name;
    private String user_type;
    private String dish_date;

    public FoodBean() {
    }

    public FoodBean(Long id) {
        this.id = id;
    }

    public FoodBean(Long id, String f_order_id, String f_goods_name, String f_goods_num, String f_user_name, String f_user_id, String f_user_phone, String f_valid_start, String f_valid_end, String group_name, String user_type, String dish_date) {
        this.id = id;
        this.f_order_id = f_order_id;
        this.f_goods_name = f_goods_name;
        this.f_goods_num = f_goods_num;
        this.f_user_name = f_user_name;
        this.f_user_id = f_user_id;
        this.f_user_phone = f_user_phone;
        this.f_valid_start = f_valid_start;
        this.f_valid_end = f_valid_end;
        this.group_name = group_name;
        this.user_type = user_type;
        this.dish_date = dish_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getF_order_id() {
        return f_order_id;
    }

    public void setF_order_id(String f_order_id) {
        this.f_order_id = f_order_id;
    }

    public String getF_goods_name() {
        return f_goods_name;
    }

    public void setF_goods_name(String f_goods_name) {
        this.f_goods_name = f_goods_name;
    }

    public String getF_goods_num() {
        return f_goods_num;
    }

    public void setF_goods_num(String f_goods_num) {
        this.f_goods_num = f_goods_num;
    }

    public String getF_user_name() {
        return f_user_name;
    }

    public void setF_user_name(String f_user_name) {
        this.f_user_name = f_user_name;
    }

    public String getF_user_id() {
        return f_user_id;
    }

    public void setF_user_id(String f_user_id) {
        this.f_user_id = f_user_id;
    }

    public String getF_user_phone() {
        return f_user_phone;
    }

    public void setF_user_phone(String f_user_phone) {
        this.f_user_phone = f_user_phone;
    }

    public String getF_valid_start() {
        return f_valid_start;
    }

    public void setF_valid_start(String f_valid_start) {
        this.f_valid_start = f_valid_start;
    }

    public String getF_valid_end() {
        return f_valid_end;
    }

    public void setF_valid_end(String f_valid_end) {
        this.f_valid_end = f_valid_end;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDish_date() {
        return dish_date;
    }

    public void setDish_date(String dish_date) {
        this.dish_date = dish_date;
    }

}
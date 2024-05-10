package lk.ijse.moonstonejewerllary.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataValidateController {

    public static boolean validateCustomerId(String id){
        String idRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean validateCustomerName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateCustomerAddress(String address){
        String addressRegex = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean validateCustomerTel(String tel){
        String telRegex = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }

    public static boolean validateCustomerEmail(String email){
        String emailRegex = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validateEmployeeName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateEmployeeTel(String tel){
        String telRegex = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }

    public static boolean validateEmployeeId(String id){
        String idRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean validateEmployeeAddress(String address){
        String addressRegex = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean validateItemCode(String code){
        String codeRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(codeRegex);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    public static boolean validateItemName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateItemQty(String qty){
        String qtyRegex = "^[0-9]{1,}$";
        Pattern pattern = Pattern.compile(qtyRegex);
        Matcher matcher = pattern.matcher(qty);
        return matcher.matches();
    }

    public static boolean validateItemPrice(String price){
        String priceRegex = "^[0-9]{1,}$";
        Pattern pattern = Pattern.compile(priceRegex);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public static boolean validateItemCategory(String category){
        String categoryRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(categoryRegex);
        Matcher matcher = pattern.matcher(category);
        return matcher.matches();
    }

    public static boolean validateItemDate(String date){
        String dateRegex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean validateOrderId(String id){
        String idRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean validateOrderDate(String date){
        String dateRegex = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static boolean validateOrderQty(String qty){
        String qtyRegex = "^[0-9]{1,}$";
        Pattern pattern = Pattern.compile(qtyRegex);
        Matcher matcher = pattern.matcher(qty);
        return matcher.matches();
    }

    public static boolean validateSupplierId(String id){
        String idRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean validateSupplierName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateSupplierTel(String tel){
        String telRegex = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
        Pattern pattern = Pattern.compile(telRegex);
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }

    public static boolean validateRegisterId(String id){
        String idRegex = "^([A-Z0-9])$";
        Pattern pattern = Pattern.compile(idRegex);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean validateRegisterName(String name){
        String nameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateRegisterPassWord(String password){
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validateLoginUserName(String username){
        String usernameRegex = "^[A-z|\\s]{3,}$";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean validateLoginPassWord(String password){
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
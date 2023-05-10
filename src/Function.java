import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class Function {
    /**
     * loops through object-attributes for first value input
     *
     * @param product
     * @throws Exception
     */
    public static Product create(Product product) throws Exception {
        boolean firstIteration = true;
        boolean secondIteration = false;
        boolean erroroccurence = false;
        boolean repeat;
        do {
            repeat = false;
            Class<? extends Product> iterator = product.getClass();
            while (iterator != null) {//while-loop iterates through Class and relative Superclasses
                for (Field field : iterator.getDeclaredFields()) {//loop through object attributes
                    field.setAccessible(true);//sets private attributes accessible
                    if (field.getType() == int.class || field.getType() == double.class) {//check if Object-attribute is int or double
                        double fieldValue = field.getDouble(product);//load value of attribute as double
                        if (firstIteration) {
                            invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                            repeat = true;
                        } else {
                            if (fieldValue == -1) {//check requirements of attribute
                                if (!secondIteration) {
                                    repeat = true;//repeater-attribute -- to doublecheck for correct input
                                    invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                                } else {
                                    erroroccurence = true;
                                }
                            }
                        }
                    } else if (field.getType() == String.class) {//check if Object-attribute is String
                        String fieldValue = (String) field.get(product);//load value of attribute as String
                        if (firstIteration){
                            invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                            repeat = true;
                        }else {
                            if (fieldValue == null) {//check requirements of attribute
                                if (!secondIteration) {
                                    repeat = true;//repeater-attribute -- to doublecheck for correct input
                                    invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                                } else {
                                    erroroccurence = true;
                                }
                            }
                        }
                    } else if (field.getType() == ArrayList.class) {//check if Object-attribute is ArrayList
                        ParameterizedType genericType = (ParameterizedType) field.getGenericType();//get generic Typ
                        Class<?> listType = (Class<?>) genericType.getActualTypeArguments()[0];//get specific  argumenttyp
                        if (firstIteration && listType == String.class){
                            invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                            repeat = true;
                        } else {
                            boolean containsError = false;
                            ArrayList<String> listValue = (ArrayList<String>) field.get(product);//get the specific values
                            for (String value: listValue){
                                String[] split = value.split(";");
                                if(split[0].equals("null")){
                                    containsError = true;
                                    break;
                                }
                            }
                            if (containsError && listType == String.class) {
                                if (!secondIteration) {
                                    repeat = true;//repeater-attribute -- to doublecheck for correct input
                                    invokeSetterWithInputValue(field.getType().getSimpleName(), product.getClass(), field.getName(), product, firstIteration);
                                } else {
                                    erroroccurence = true;
                                }
                            }
                        }
                    }
                }
                iterator = (Class<? extends Product>) iterator.getSuperclass();//switch to superclass of object
            }
            if (secondIteration){//condition for switch to mainMenu if one or more attributes are false input
                if (erroroccurence) {
                    CU.consoleErrorSeparatorLine(true);
                    System.out.println("Produkt konnte aufgrund leerer/falscher Eingabewerte nicht gespeichert werden");
                    CU.consoleErrorSeparatorLine(true);
                    Menu.correction();
                }
                secondIteration = false;
                repeat =true;
            }
            if (firstIteration) {
                secondIteration = true;
            }
            firstIteration = false;
        }while(repeat);
    return product;
    }

    private static void invokeSetterWithInputValue(String simpleName, Class<?> aClass, String fieldName, Object product, boolean firstIteration) throws Exception {
        String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);//set a String for Method invocation
        Method getterMethodLanguageString = CCS.class.getMethod(getterMethodName);//sets a getter method for custom constant strings
        ArrayList<String> availableValueList = new ArrayList<>();
        try{
            Method getterMethodValueList = CC.class.getMethod(getterMethodName);//sets a getter method for custom constants
            availableValueList = (ArrayList<String>) getterMethodValueList.invoke(null);//assign ValueList if available
        } catch (NoSuchMethodException e) {
            //nothing to do if Valuelist isn't available
        }
        CU.consoleSeparatorLine(true);
        if (!firstIteration) {
            System.err.println("Fehlerhafte Eingabe für " + getterMethodLanguageString.invoke(null));
            CU.pause(250);
        }
        String value = "";
        if (simpleName.equals("ArrayList")) {
            System.out.println("Werte für " + getterMethodLanguageString.invoke(null) + ":");
            if (!firstIteration) {
                String getterMethodArrayListName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getterMethodArrayList = aClass.getMethod(getterMethodArrayListName);
                ArrayList<String> valueList;
                valueList = (ArrayList<String>) getterMethodArrayList.invoke(product);
                if (availableValueList.size() != 0) {
                    System.out.println("Eingabemöglichkeiten");
                    for (String element : availableValueList) {
                        System.out.println(element);
                    }
                }
                System.out.println("(Um Wert aus Liste zu entfernen, del, löschen, delete)");
                String valuePart = "";
                boolean conti;
                for (int i = 1; i <= valueList.size(); i++){
                    CU.consoleSeparatorLine(false);
                    if (i > 1) {
                        value += ",";
                    }
                    conti = false;
                    for (String listValue: valueList){
                        for (String listAvailableValue: availableValueList){
                            if(listValue.equals(listAvailableValue)){
                                conti = true;
                            }
                        }
                    }
                    if (conti) {
                        value += valueList.get(i-1);
                        continue;
                    }
                    String[] split = valueList.get(i-1).split(";");
                    if (split.length>1) {
                        System.out.print(i + ". Wert " + split[1] + " korrigieren: ");
                    }
                    valuePart = CU.sc.nextLine();
                    if( valuePart.equalsIgnoreCase("esc") ||
                        valuePart.equalsIgnoreCase("entfernen") ||
                        valuePart.equalsIgnoreCase("del") ||
                        valuePart.equalsIgnoreCase("löschen") ||
                        valuePart.equalsIgnoreCase("delete")){
                        valueList.remove(i-1);
                        continue;
                    }
                    if (availableValueList.size() != 0) {//Vergleich
                        boolean inList = false;
                        for (String element : availableValueList) {
                            if (valuePart.equalsIgnoreCase(element)) {
                                inList = true;
                                valuePart = element;
                                break;
                            }
                        }
                        if (!inList) {
                            i--;
                        }
                    }
                    value += valuePart;
                }
            } else {
                System.out.println("(Zum beenden, esc, abbruch oder ende)");
                String valuePart = "";
                for (int i = 1; i >= 0; i++) {
                    CU.consoleSeparatorLine(false);
                    if (i > 1) {
                        value += ",";
                    }
                    System.out.print(i + ". Wert eingeben: ");
                    valuePart = CU.sc.nextLine();
                    if( valuePart.equalsIgnoreCase("esc") ||
                        valuePart.equalsIgnoreCase("abbruch") ||
                        valuePart.equalsIgnoreCase("beenden") ||
                        valuePart.equalsIgnoreCase("ende") ||
                        valuePart.equalsIgnoreCase("exit")) {
                        break;
                    }
                    if (availableValueList.size() != 0) {//Vergleich
                        boolean inList = false;
                        for (String element : availableValueList) {
                            if (valuePart.equalsIgnoreCase(element)) {
                                inList = true;
                                valuePart = element;
                                break;
                            }
                        }
                        if (!inList) {
                            valuePart = null + ";" + valuePart;
                        }
                    }
                    //EndeVergleich
                    value += valuePart;
                }
            }
        } else{
            System.out.print("Wert für " + getterMethodLanguageString.invoke(null) + ": ");
            value = CU.sc.nextLine();
            if (availableValueList.size() != 0){
                boolean inList = false;
                for (String element: availableValueList){
                    if (value.equals(element)){
                        inList = true;
                        break;
                    }
                }
                if (!inList){
                    value = null;
                }
            }
        }
        String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method setterMethod = aClass.getMethod(setterMethodName, String.class);
        setterMethod.invoke(product,value);
    }
    private static void search(){

    }

    public static void edit(Product product) throws Exception {
        Class<? extends Product> iterator = product.getClass();
        while (iterator != null){
            for (Field field : iterator.getDeclaredFields()) {
                String getterMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                Method getterMethodLanguageString = CCS.class.getMethod(getterMethodName);
                getterMethodLanguageString.invoke(null);
            }
        }
    }
}
/*
    public static void check(Product product) throws Exception {

        boolean repeat;
        do {
            Class<? extends Product> iterator = product.getClass();
            repeat = false;
            while (iterator != null) {//while-loop iterates through Class and relative Superclasses
                for (Field field : iterator.getDeclaredFields()) {//loop through object attributes
                    field.setAccessible(true);//sets private attributes accessible
                    if (!field.getName().equals("properties")) {//skip the HashMap<> properties attributes
                        if (field.getType() == int.class || field.getType() == double.class) {//check if Object-attribute is int or double
                            double fieldValue = field.getDouble(product);//load value of attribute as double
                            if (fieldValue == -1) {//check requirements of attribute
                                repeat = true;//repeater-attribute -- to doublecheck for correct input
                                invokeSetterWithInputValue(product.getClass(), field.getName(), product, firstIteration);
                            }
                        } else if (field.getType() == String.class) {//check if Object-attribute is String

                            String fieldValue = (String) field.get(product);//load value of attribute as String
                            if (fieldValue == null) {//check requirements of attribute
                                repeat = true;//repeater-attribute -- to doublecheck for correct input
                                invokeSetterWithInputValue(product.getClass(), field.getName(), product, firstIteration);
                            }
                        } else if (field.getType() == ArrayList.class) {//check if Object-attribute is ArrayList
                            ParameterizedType genericType = (ParameterizedType) field.getGenericType();//get generic Type
                            Class<?> listType = (Class<?>) genericType.getActualTypeArguments()[0];//get specific  argumenttyp

                            List<?> listValue = (List<?>) field.get(product);//get the specific values
                            if (listValue != null && listType == Integer.class && listValue.contains(-1)) {
                                repeat = true;//repeater-attribute -- to doublecheck for correct input
                                invokeSetterWithInputValue(product.getClass(), field.getName(), product, firstIteration);
                            }
                        } else if (field.getType() == HashMap.class) {//check if Object-attribute is HashMap
                            Class<?> keyType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];// gets the type of keys
                            Class<?> valueType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1];// gets the type of Values

                            Map<?, ?> mapValue = (Map<?, ?>) field.get(product);//get value of field with wildcard
                            if (mapValue != null && keyType == String.class && valueType == Integer.class) {
                                boolean containsErrorValue = false;//helper-attribute to check if Error-input is present
                                for (Map.Entry<?, ?> entry : mapValue.entrySet()) {//loop for every hasMap-Entry
                                    if (entry.getKey() == null || entry.getValue() == null || entry.getValue().toString().equals("-1")) {
                                        containsErrorValue = true;
                                        break;
                                    }
                                }
                                if (containsErrorValue) {//check requirements of attribute
                                    repeat = true;//repeater-attribute -- to doublecheck for correct input
                                    invokeSetterWithInputValue(product.getClass(), field.getName(), product, firstIteration);
                                }
                            }
                        }
                    }
                }

                iterator = (Class<? extends Product>) iterator.getSuperclass();//switch to superclass of object
            }
        }while(repeat);
    }
    */
    /*
        HashMap<String, String> properties = new HashMap<>();
        CU.consoleEmptyLine(50);
        System.out.println("Daten für " + product.getTyp() + " eingeben.\n");
        for (String x: product.pullProperties().keySet()){
            System.out.print(x + " eingeben:");
            properties.put(x, CU.sc.nextLine());
            System.out.println();
        }
        product.pushProperties(properties);
        check(product);
        Main.products.add(product);

         */

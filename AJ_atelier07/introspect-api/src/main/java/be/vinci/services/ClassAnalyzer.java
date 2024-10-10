package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.*;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());
        return objectBuilder.build();
    }

    //TODO getMethods
    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(Method m : aClass.getDeclaredMethods()) {
                arrayBuilder.add(getMethod(m));
        }
        return arrayBuilder.build();
    }

    //TODO getMethod
    public JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", m.getName());
        objectBuilder.add("returnType:", String.valueOf(m.getReturnType()));
        //todo boucler sur chaque type de param
        objectBuilder.add("parameters", String.valueOf(m.getParameters()));
        //objectBuilder.add("visibility", Modifier.is);
        objectBuilder.add("isStatic", Modifier.isStatic(m.getModifiers()));
        objectBuilder.add("isAbstract", Modifier.isAbstract(m.getModifiers()));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        for (Field f : aClass.getDeclaredFields()){
            arrayBuilder.add(getField(f));
        }
        return arrayBuilder.build();
    }

    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        objectBuilder.add("name", f.getName());
        if (f.getGenericType() instanceof ParameterizedType) {
            objectBuilder.add("type", f.getGenericType().toString());
        } else {
            objectBuilder.add("type", f.getType().getSimpleName());
        }
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        return Modifier.isStatic(f.getModifiers());
        // TODO
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        // TODO
        if(Modifier.isPublic(f.getModifiers())){
            return "public";
        }
        else if(Modifier.isPrivate(f.getModifiers())) {
            return "private";
        }
        else if(Modifier.isProtected(f.getModifiers())){
            return "protected";
        }
        return "package";
    }

}

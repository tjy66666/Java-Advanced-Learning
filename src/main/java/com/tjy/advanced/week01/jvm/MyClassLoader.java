package com.tjy.advanced.week01.jvm;

import java.util.Base64;

/**
 * @author tjy
 * @date 2023/8/4
 * @apiNote
 */
public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        new MyClassLoader().findClass("com.tjy.advanced.week01.jvm.Hello").newInstance();
    }

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEAGEhlbGxvIGNsYXNzIOWKoOi9veS6hiEhIQcAGQwAGgAbAQAhY29tL3RqeS9hZHZhbmNlZC93ZWVrMDEvanZtL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAdAAEAAQAAAAUqtwABsQAAAAEACgAAAAYAAQAAAAgACAALAAgAAQAJAAAAJQACAAAAAAAJsgACEgO2AASxAAAAAQAKAAAACgACAAAACgAIAAsAAQAMAAAAAgAN";
        byte[] bytes = decode(helloBase64);
        return defineClass(name,bytes,0, bytes.length);
    }
    public byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}

package com.doubledimple.ignite.ignitecache.config.datasource;

public class constant {

    private static final String MAPPER_PACKAGE = "com.doubledimple.ignite.ignitecache.mapper.";
    private static final String MAPPER_XML = "classpath:mapper/";

    public static final String MAPPER_DS1 = MAPPER_PACKAGE + "dao1";
    public static final String MAPPER_DS2 = MAPPER_PACKAGE + "dao2";

    public static final String MAPPER_XML_LOCATION_DS1 = MAPPER_XML + "mapper1/**/*.xml";
    public static final String MAPPER_XML_LOCATION_DS2 = MAPPER_XML + "mapper2/**/*.xml";

}

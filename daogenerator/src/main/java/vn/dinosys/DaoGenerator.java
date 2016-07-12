package vn.dinosys;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGenerator {

    public static final String JAVA_PACKAGE = "vn.dinosys.dinoad.data.database.table.dao";
    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");

    public static void main(String args[]) throws Exception {
        DaoGenerator generator = new DaoGenerator();

        Schema schema = new Schema(1, JAVA_PACKAGE);
        generator.addUserInfo(schema);

        String outDir = "/app/src/main/java";
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, PROJECT_DIR + outDir);
    }

    private void addUserInfo(Schema pSchema) {
        Entity note = pSchema.addEntity("UserInfo");
        note.implementsSerializable();

        note.addLongProperty("UserId");
        note.addStringProperty("DisplayName");
        note.addShortProperty("CountryCode");
        note.addStringProperty("PhoneNumber");
        note.addStringProperty("Platform");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author nhile
 */
public enum ButtonEnum {

    CreateFolder("CREATEFOLDER"),CreateFile("CREATEFILE"), Rename("RENAME"), Delete("DELETE"), Move("MOVE"), Copy("COPY"),Reset("RESET");
    private String value;

    private ButtonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ButtonEnum convertToEnum(String key) {
        switch (key) {

            case "CREATEFOLDER": {
                return CreateFolder;
            }
            
            case "CREATEFILE": {
                return CreateFile;
            }
            case "RENAME": {
                return Rename;
            }
            case "DELETE": {
                return Delete;
            }
            case "MOVE": {
                return Move;
            }
            case "COPY": {
                return Copy;
            }
            case "RESET": {
                return Reset;
            }
        }
        return null;
    }

}

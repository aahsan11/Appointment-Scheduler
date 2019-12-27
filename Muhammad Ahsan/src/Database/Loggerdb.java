/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author mhamza0
 */
public class Loggerdb {
    
 private final static Logger logger = Logger.getLogger(Loggerdb.class.getName());
 private static FileHandler filehandler = null;
 
 public static void init(){
    try {
    filehandler = new FileHandler("log.%u.%g.txt", 1024 * 1024, 10, true);
    } catch (SecurityException | IOException e) {
        e.printStackTrace();
        }
    Logger logger = Logger.getLogger("");
    filehandler.setFormatter(new SimpleFormatter());
    logger.addHandler(filehandler);
    logger.setLevel(Level.INFO);
 }
    
}

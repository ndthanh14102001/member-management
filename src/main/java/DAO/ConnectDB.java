/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity._Device;
import Entity._Member;
import Entity._Processing;
import Entity._UsageInformation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Admin
 */
public class ConnectDB {

    private SessionFactory factory;

    public ConnectDB() {
        try {
            Configuration configuration = new Configuration();

            configuration.addAnnotatedClass(_Member.class);
            configuration.addAnnotatedClass(_Device.class);
            configuration.addAnnotatedClass(_Processing.class);
            configuration.addAnnotatedClass(_UsageInformation.class);
            configuration.configure("hibernate.cfg.xml");

            factory = configuration.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }

}

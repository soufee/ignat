package main.java;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * Created by admin on 26.05.2017.
 */
public class DataGridStarter {
  //  static final Logger userLogger = LogManager.getLogger(ConnectionFactory.class);
public static final int MILLION = 100000;
public static final int MAXRAND = 1000;
    public static void main(String[] args) {
        Ignition.start();
        Ignite ignite = Ignition.ignite();
        final IgniteCache<Integer, String> igniteCache = ignite.createCache("kotlin");
        Random random = new Random();
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < MILLION; i++) {
            igniteCache.put(random.nextInt(MAXRAND), random.nextInt(MAXRAND) + "");
        }
        System.out.println("Время добавления в Игнат " + (System.currentTimeMillis()-starttime));
        try {
            Connection connection = ConnectionFactory.getConnection();
            long start = System.currentTimeMillis();
            for (int i = 0; i <MILLION ; i++) {


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.ignat_table (id,string) VALUES (?,?)");
            preparedStatement.setInt(1,random.nextInt(MAXRAND));
            preparedStatement.setString(2,random.nextInt(MAXRAND)+"");
            preparedStatement.execute();
            }
            System.out.println("Время добавления в БД " + (System.currentTimeMillis()-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }


    }
}

package controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.util.Random;

public class MainStage {
    public static Stage primaryStage;
    static boolean isAlreadyRunning = false;
    public static void StartCancer(){
        if (!isAlreadyRunning){
            CancerThread ct = new CancerThread();
            ct.start();
        }
    }

    private static Random r = new Random();

    public static class CancerThread extends Thread{

        @Override
        public synchronized void start() {
            isAlreadyRunning = true;
            run2();
            isAlreadyRunning = false;
        }

        public void run2() {

            try{
                if(primaryStage != null){
                    Scene scene = primaryStage.getScene();

                    Parent parent = scene.getRoot();

                    applyCancer(parent);

                }else{
                }
            }catch (Exception e){
                try{
                }catch (Exception e2){
                    return;
                }
            }
        }

        private void applyCancer(Parent parent){
            if(parent != null){
                parent.setRotate(parent.getRotate() + r.nextDouble()*10);
                parent.setScaleX(r.nextDouble() + 0.5);
                parent.setScaleX(r.nextDouble() + 0.5);
                parent.setScaleX(r.nextDouble() + 0.5);

                for (Node n : parent.getChildrenUnmodifiable()) {
                    if(n instanceof Parent)
                        applyCancer((Parent) n);
                }
            }
        }
    }
}

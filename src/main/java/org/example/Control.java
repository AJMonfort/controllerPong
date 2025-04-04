package org.example;

import net.java.games.input.*;

public class Control implements Runnable {

    Paddle paddle1;
    Paddle paddle2;

    public Control(Paddle paddle1, Paddle paddle2) {
        /* Get the available controllers */
//        Controller[] controllers = ControllerEnvironment
//                .getDefaultEnvironment().getControllers();
//        Controller player1 = null;
//        if (controllers.length == 0) {
//            System.out.println("Found no controllers.");
//            System.exit(0);
//        }
//        for(int i = 0; i < controllers.length; i++) {
//            if(controllers[i].getType() == Controller.Type.GAMEPAD) {
//                System.out.println("SUCCESS!!");
//                player1 = controllers[i];
//            }
//            //System.out.println(controllers[i].getType());
//        }
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }

    public void updatePaddles(Paddle paddle1, Paddle paddle2) {
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }

    public void run() {

        while (true) {
            /* Get the available controllers */
            Controller[] controllers = ControllerEnvironment
                    .getDefaultEnvironment().getControllers();
            Controller player1 = null;
            if (controllers.length == 0) {
                System.out.println("Found no controllers.");
                System.exit(0);
            }
            for(int i = 0; i < controllers.length; i++) {
                if(controllers[i].getType() == Controller.Type.GAMEPAD) {
                    //System.out.println("SUCCESS!!");
                    player1 = controllers[i];
                }
            }

            //for (int i = 0; i < controllers.length; i++) {

                /* Remember to poll each one */
                //controllers[i].poll();
            //player1.poll();

                /* Get the controllers event queue */
                //EventQueue queue = controllers[i].getEventQueue();
            EventQueue queue = player1.getEventQueue();

                /* Create an event object for the underlying plugin to populate */
                Event event = new Event();
                Boolean stopped = false;

                while(!stopped) {
                    player1.poll();
//                    queue.getNextEvent(event);
//                    Component component = event.getComponent();

                    for(Component component : player1.getComponents()) {
//                        Component.Identifier identifier = component.getIdentifier();
//                        float data = component.getPollData();
                        if(component.getIdentifier() == Component.Identifier.Axis.Y) {
                            float data = component.getPollData();
                            //System.out.println("Y value: " + data);
                            if(data > .2 || data < -.2) {
                                System.out.println("Y value: " + data);
                                paddle1.controllerInput(data);
                            }
                            //paddle1.controllerInput(data);
                            else {
                                paddle1.controllerInput(0);
                            }
                        }
                        else if(component.getIdentifier() == Component.Identifier.Axis.RZ) {
                            float data = component.getPollData();
                            //System.out.println("RZ value: " + data);
                            if(data > .2 || data < -.2) {
                                System.out.println("RZ value: " + data);
                                paddle2.controllerInput(data);
                            }
                            //paddle2.controllerInput(data);
                            else {
                                paddle2.controllerInput(0);
                            }
                        }
//                        else if(identifier == Component.Identifier.Axis.RZ) {
//                            System.out.println("Z value: " + data);
//                            if(data > .4 || data < -.4) {
//                                System.out.println(data);
//                                paddle2.controllerInput(data);
//                            }
//                        }
                    }
                }

                /* For each object in the queue */
//                while (queue.getNextEvent(event)) {
//
//                    /*
//                     * Create a string buffer and put in it, the controller name,
//                     * the time stamp of the event, the name of the component
//                     * that changed and the new value.
//                     *
//                     * Note that the timestamp is a relative thing, not
//                     * absolute, we can tell what order events happened in
//                     * across controllers this way. We can not use it to tell
//                     * exactly *when* an event happened just the order.
//                     */
//                    StringBuffer buffer = new StringBuffer(player1//controllers[i]
//                            .getName());
//                    buffer.append(" at ");
//                    buffer.append(event.getNanos()).append(", ");
//                    Component comp = event.getComponent();
//                    buffer.append(comp.getName()).append(" changed to ");
//                    float value = event.getValue();
//
//                    /*
//                     * Check the type of the component and display an
//                     * appropriate value
//                     */
//                    if (comp.isAnalog()) {
//                        buffer.append(value);
//                    } else {
//                        if (value == 1.0f) {
//                            buffer.append("On");
//                        } else {
//                            buffer.append("Off");
//                        }
//                    }
//                    System.out.println(buffer.toString());
//                }
            //}

            /*
             * Sleep for 20 milliseconds, in here only so the example doesn't
             * thrash the system.
             */
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

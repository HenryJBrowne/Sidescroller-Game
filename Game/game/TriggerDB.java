import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

public class TriggerDB {

    private HashMap<Integer, ArrayList<Trigger>> triggersForLevels = new HashMap<>();

    /**
     * Every level will have a list of triggers defined here
     */
    public TriggerDB() {
    }

    public ArrayList<Trigger> getTriggers(int id, Screen s) {
        if (!triggersForLevels.containsKey(id)) {
            switch (id) {
                case 1:
                    triggersForLevels.put(id, getTriggers1(s));
                    break;
                case 2:
                    triggersForLevels.put(id, getTriggers2(s));
                    break;
                case 3:
                    triggersForLevels.put(id, getTriggers3(s));
                    break;
                case 4:
                    triggersForLevels.put(id, getTriggers4(s));
                    break;
            }
        }
        return triggersForLevels.get(id);
    }

    private ArrayList<Trigger> getTriggers1(Screen s) {
        // ---------------------------------Level 1------------------------------------
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(new Vector2f(850, 200), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getMainCharacter().showInvent(false);
                s.getLevel().getShop().show(s.getWindow(),
                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()));
            }
        }));
        triggers.add(new Trigger(new Vector2f(890, 200), new Vector2f(32, 300), new Runnable() {

            @Override
            public void run() {
                s.getLevel().getShop().close(); // prevent from input checking, after the shop
            }
        }));
        triggers.add(new Trigger(new Vector2f(805, 200), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getLevel().getShop().close(); // prevent from input checking, before the shop
            }
        }));

        triggers.add(new Trigger(new Vector2f(9020, 0), new Vector2f(100, 1000), new Runnable() {

            @Override
            public void run() {
                if (s.getLevel().getBoss().isDead()) {
                    s.saveStateOfLevel(); // save a level to know what is the next one
                    s.setLevel(new Level(-4));
                    s.isMenuShown(false);
                } else {
                    s.showEndLevelWarning(new Vector2f(13945, 1010));
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(2590, 156), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(2595, 254), s.getWindow());// set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(5815, 380), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(5822, 480), s.getWindow());// set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(7738, 188), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(7748, 288), s.getWindow());// set spawn point
            }
        }));
        return triggers;
    }

    private ArrayList<Trigger> getTriggers2(Screen s) {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(new Vector2f(941, 700), new Vector2f(50, 400), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().showInvent(false);
                s.getLevel().getShop().show(s.getWindow(), // show the shop
                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()));
            }

        }));
        triggers.add(new Trigger(new Vector2f(891, 700), new Vector2f(30, 400), new Runnable() {

            @Override
            public void run() {
                s.getLevel().getShop().close(); // prevent from input checking, before the shop
            }
        }));
        triggers.add(new Trigger(new Vector2f(1010, 700), new Vector2f(30, 400), new Runnable() {

            @Override
            public void run() {
                s.getLevel().getShop().close(); // prevent from input checking, before the shop
            }
        }));
        triggers.add(new Trigger(new Vector2f(3540, 760), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(3540, 830), s.getWindow());// set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(7100, 550), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(7100, 620), s.getWindow());// set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(10500, 500), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(10500, 570), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(10930, 0), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(10930, 80), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(12940, 900), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(12940, 970), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(14300, 400), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(14300, 460), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(13945, 1010), new Vector2f(50, 100), new Runnable() {

            @Override
            public void run() {
                if (s.getLevel().getBoss().isDead()) {
                    s.saveStateOfLevel();
                    s.setLevel(new Level(-4));
                    s.isMenuShown(false);
                } else {
                    s.showEndLevelWarning(new Vector2f(13815, 960));
                }
            }
        }));

        triggers.add(new Trigger(new Vector2f(11050, 0), new Vector2f(50, 100), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(0).isDone()) {
                        o.isActive(true);
                        o.loadConversations(s.getLevel().getNumber());
                        o.showConversation(0, s);// first encounter
                        s.getMainCharacter().modifyMoney(-100);
                    }
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(14056, 450), new Vector2f(100, 100), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(1).isDone()) {
                        o.showConversation(1, s);// before Boss
                    }
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(14130, 920), new Vector2f(50, 100), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (s.getLevel().getBoss().isDead()) {
                        if (!o.getConversation(2).isDone()) {
                            o.showConversation(2, s);// after Boss
                        }
                    }
                }
            }
        }));
        return triggers;
    }

    private ArrayList<Trigger> getTriggers3(Screen s) {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(new Vector2f(402, 616), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getMainCharacter().showInvent(false);
                s.getLevel().getShop().show(s.getWindow(),
                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()));
            }
        }));
        triggers.add(new Trigger(new Vector2f(450, 616), new Vector2f(32, 300), new Runnable() {

            @Override
            public void run() {
                s.getLevel().getShop().close();
            }
        }));
        triggers.add(new Trigger(new Vector2f(350, 616), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getLevel().getShop().close();
            }
        }));
        triggers.add(new Trigger(new Vector2f(1650, 700), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(1704, 800), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(3730, 520), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(3783, 624), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(5760, 540), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(5812, 640), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(6610, 732), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(6670, 832), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(7470, 340), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(7529, 448), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(8000, 476), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(8063, 576), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(9900, 900), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(9946, 990), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(11750, 555), new Vector2f(60, 130), new Runnable() {
            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(11810, 655), s.getWindow()); // set spawn point
            }

        }));
        triggers.add(new Trigger(new Vector2f(14200, 500), new Vector2f(100, 1000), new Runnable() {

            @Override
            public void run() {
                if (s.getLevel().getBoss().isDead()) {
                    s.saveStateOfLevel(); // save a level to know what is the next one
                    s.setLevel(new Level(-4));
                    s.isMenuShown(false);
                } else {
                    s.showEndLevelWarning(new Vector2f(14269, 976));
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(312, 824), new Vector2f(30, 100), new Runnable() {

            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    o.isActive(true); // activate orvil
                    o.loadConversations(s.getLevel().getNumber());
                }
            }
        }));

        triggers.add(new Trigger(new Vector2f(695, 824), new Vector2f(30, 100), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(0).isDone()) {
                        o.showConversation(0, s);// start level
                        o.setPosition(new Vector2f(12260, 650)); // teleport to end of level
                    }
                }
            }
        }));

        triggers.add(new Trigger(new Vector2f(12350, 580), new Vector2f(30, 100), new Runnable() {

            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(1).isDone()) {
                        o.showConversation(1, s);// before boss
                    }
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(14065, 850), new Vector2f(30, 200), new Runnable() {

            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (s.getLevel().getBoss().isDead())
                        if (!o.getConversation(2).isDone()) {
                            o.showConversation(2, s);// after boss
                        }
                }
            }
        }));
        return triggers;
    }

    private ArrayList<Trigger> getTriggers4(Screen s) {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(new Vector2f(2064, 512), new Vector2f(16, 48), new Runnable() {

            @Override
            public void run() {
                if (s.getMainCharacter().haskey()) {
                    s.getWorld().openDoor(s.getMainCharacter().getCenter());
                    s.getMainCharacter().removeKey();
                } else {
                    s.showDoorWarning((new Vector2f(2000, 462)));
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(752, 328), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getMainCharacter().showInvent(false);
                s.getLevel().getShop().show(s.getWindow(),
                        s.getWindow().mapPixelToCoords(Mouse.getPosition(s.getWindow()), s.getWindow().getView()));
            }
        }));
        triggers.add(new Trigger(new Vector2f(704, 328), new Vector2f(32, 300), new Runnable() {

            @Override
            public void run() {
                s.getLevel().getShop().close();
            }
        }));
        triggers.add(new Trigger(new Vector2f(800, 328), new Vector2f(32, 300), new Runnable() {
            @Override
            public void run() {
                s.getLevel().getShop().close();
            }
        }));
        triggers.add(new Trigger(new Vector2f(3715, 284), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(3725, 360), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(3490, 860), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(3500, 920), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(6020, 410), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(6029, 490), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(8240, 250), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(8255, 330), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(11580, 580), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(11590, 675), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(16250, 550), new Vector2f(60, 130), new Runnable() {

            @Override
            public void run() {
                s.getMainCharacter().setSpawn(new Vector2f(16267, 665), s.getWindow()); // set spawn point
            }
        }));
        triggers.add(new Trigger(new Vector2f(19284, 400), new Vector2f(60, 530), new Runnable() {

            @Override
            public void run() {
                if (s.getLevel().getBoss().isDead()) {
                    ConversationWindow cW = new ConversationWindow();
                    cW.setConversation(getReubenText());
                    cW.show(s);
                    s.getWindow().setView(s.getWindow().getDefaultView());
                    new StoryScreen(getAboutInfo(s.getWindow().getDefaultView().getCenter()), s);
                    s.setLevel(new Level(0)); // go to main menu
                    s.isMenuShown(false);
                } else {
                    s.showEndLevelWarning(new Vector2f(19284, 400));
                }
            }

            private ArrayList<String> getReubenText() {
                ArrayList<String> list = new ArrayList<>();
                try {
                    Scanner scanner = new Scanner(new File("additionalFiles/Reuben.txt"));
                    while (scanner.hasNextLine()) {
                        list.add(scanner.nextLine());
                    }
                    scanner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return list;
            }

            private String getAboutInfo(Vector2f pos) {
                String about = "";
                try {
                    Scanner scanner = new Scanner(new File("additionalFiles/aboutInfo.txt"));
                    while (scanner.hasNextLine()) {
                        about += scanner.nextLine() + "\n";
                    }
                    scanner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return about;
            }
        }));
        triggers.add(new Trigger(new Vector2f(352, 490), new Vector2f(30, 100), new Runnable() {

            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    o.isActive(true); // activate orvil
                    o.loadConversations(s.getLevel().getNumber());
                }
            }
        }));

        triggers.add(new Trigger(new Vector2f(1830, 200), new Vector2f(16, 130), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(0).isDone()) {
                        o.showConversation(0, s);// start level
                        o.setPosition(new Vector2f(16484, 688)); // teleport to end of level
                    }
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(17900, 556), new Vector2f(16, 130), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (!o.getConversation(1).isDone()) {
                        o.showConversation(1, s);// before boss
                    }
                }
            }
        }));

        triggers.add(new Trigger(new Vector2f(18304, 566), new Vector2f(16, 130), new Runnable() {
            @Override
            public void run() {
                if (s.getMainCharacter().haskey()) {
                    s.getWorld().openDoor(s.getMainCharacter().getCenter());
                    s.getMainCharacter().removeKey();
                } else {
                    s.showDoorWarning((new Vector2f(18254, 600)));
                }
            }
        }));
        triggers.add(new Trigger(new Vector2f(18004, 566), new Vector2f(316, 130), new Runnable() {
            @Override
            public void run() {
                Orvil o = s.getWorld().getOrvil();
                if (o != null) {
                    if (s.getLevel().getBoss().isDead()) {
                        if (!o.getConversation(2).isDone()) {
                            o.showConversation(2, s);// after Boss
                        }
                    }
                }
            }
        }));
        return triggers;
    }
}

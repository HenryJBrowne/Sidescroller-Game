import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.Texture;

public class TextureFactory {

        private HashMap<String, String[][]> pathsAttacks = new HashMap<>();
        private HashMap<String, Texture[][]> texturesAttacks = new HashMap<>();
        private HashMap<String, Texture> textureIcons = new HashMap<>();
        private HashMap<String, String> pathsToIcon = new HashMap<>();
        private HashMap<String, Texture[]> textureProjectiles = new HashMap<>();
        private HashMap<String, String[]> pathsToProjectiles = new HashMap<>();
        private Texture[] textureLoadingScreen;
        private String[] pathsToLoadingScreen;

        public TextureFactory() {
                pathsAttacks.put("Battle Axe",
                                new String[][] { { "assets/Character/Character_Right_Attack1Axe.png",
                                                "assets/Character/Character_Right_Attack2Axe.png",
                                                "assets/Character/Character_Right_Attack1Axe.png" },
                                                { "assets/Character/Character_Left_Attack1Axe.png",
                                                                "assets/Character/Character_Left_Attack2Axe.png",
                                                                "assets/Character/Character_Left_Attack1Axe.png" } });
                pathsAttacks.put("Ice Sword",
                                new String[][] { { "assets/Character/Character_Right_Attack1B.png",
                                                "assets/Character/Character_Right_Attack2B.png",
                                                "assets/Character/Character_Right_Attack1B.png" },
                                                { "assets/Character/Character_Left_Attack1B.png",
                                                                "assets/Character/Character_Left_Attack2B.png",
                                                                "assets/Character/Character_Left_Attack1B.png" } });
                pathsAttacks.put("Iron Sword",
                                new String[][] { { "assets/Character/Character_Right_Attack1.png",
                                                "assets/Character/Character_Right_Attack2.png",
                                                "assets/Character/Character_Right_Attack1.png" },
                                                { "assets/Character/Character_Left_Attack1.png",
                                                                "assets/Character/Character_Left_Attack2.png",
                                                                "assets/Character/Character_Left_Attack1.png" } });
                pathsAttacks.put("Blood Sword",
                                new String[][] { { "assets/Character/Character_Right_Attack1R.png",
                                                "assets/Character/Character_Right_Attack2R.png",
                                                "assets/Character/Character_Right_Attack1R.png" },
                                                { "assets/Character/Character_Left_Attack1R.png",
                                                                "assets/Character/Character_Left_Attack2R.png",
                                                                "assets/Character/Character_Left_Attack1R.png" } });
                pathsAttacks.put("Default", new String[][] { { "assets/Character/Character_Right1.png" },
                                { "assets/Character/Character_Left1.png" } });
                pathsAttacks.put("Bow",
                                new String[][] { { "assets/Character/Character_Right_AttackBow1.png",
                                                "assets/Character/Character_Right_AttackBow2.png",
                                                "assets/Character/Character_Right_AttackBow1.png" },
                                                { "assets/Character/Character_Left_AttackBow1.png",
                                                                "assets/Character/Character_Left_AttackBow2.png",
                                                                "assets/Character/Character_Left_AttackBow1.png" } });

                pathsToIcon.put("Bow", "assets/Projectiles/BowIcon.png");
                pathsToIcon.put("Iron Sword", "assets/Melee_Weapons/Grey_Sword.png");
                pathsToIcon.put("Ice Sword", "assets/Melee_Weapons/Blue_Sword.png");
                pathsToIcon.put("Blood Sword", "assets/Melee_Weapons/Red_Sword.png");
                pathsToIcon.put("Battle Axe", "assets/Melee_Weapons/Axe.png");
                pathsToIcon.put("Throwing Dagger", "assets/Projectiles/DaggerIcon.png");
                pathsToIcon.put("Bomb", "assets/Projectiles/Bomb.png");
                pathsToIcon.put("Health Potion", "assets/Potions/HealthPotion.png");
                pathsToIcon.put("Mana Potion", "assets/Potions/BluePotion.png");
                pathsToIcon.put("Invincibility Potion", "assets/Potions/YellowPotion.png");
                pathsToIcon.put("Steel Armor", "assets/Armor/Armor1.png");
                pathsToIcon.put("Animite Armor", "assets/Armor/Armor2.png");
                pathsToIcon.put("Malumite Armor", "assets/Armor/Armor3.png");
                pathsToIcon.put("Money", "assets/Icons/money.png");
                pathsToIcon.put("Key", "assets/Icons/GoldenKey.png");
                pathsToIcon.put("Chest", "assets/Tiles/BackgroundAssets/Chest_Open.png");
                pathsToIcon.put("Blink", "assets/Tiles/Tutorial/BLINK.png");
                pathsToIcon.put("Frostbite", "assets/Tiles/Tutorial/FROSTBITE.png");

                pathsToProjectiles.put("Arrow", new String[] { "assets/Projectiles/ArrowRight.png",
                                "assets/Projectiles/ArrowLeft.png" });
                pathsToProjectiles.put("Bomb",
                                new String[] { "assets/Projectiles/BombNew.png", "assets/Projectiles/BombNew.png" });
                pathsToProjectiles.put("Fireball1", new String[] { "assets/projectiles/Boss1FireBall.png",
                                "assets/projectiles/Boss1FireBall.png" });
                pathsToProjectiles.put("Fireball2", new String[] { "assets/projectiles/Boss2FireBall.png",
                                "assets/projectiles/Boss2FireBall.png" });
                pathsToProjectiles.put("Dagger", new String[] { "assets/Projectiles/ThrowingDagger_Right1.png",
                                "assets/Projectiles/ThrowingDagger_Left1.png" });
                pathsToProjectiles.put("Iceball1", new String[] { "assets/projectiles/Boss3IceBall.png",
                                "assets/projectiles/Boss3IceBall.png" });

                pathsToLoadingScreen = new String[] { "assets/Menu/LoadingScreen1.png",
                                "assets/Menu/LoadingScreen2.png", "assets/Menu/LoadingScreen3.png",
                                "assets/Menu/LoadingScreen4.png", "assets/Menu/LoadingScreen5.png", };
                textureLoadingScreen = new Texture[pathsToLoadingScreen.length];
        }

        public Texture[][] getAnimation(String key) {
                if (!texturesAttacks.containsKey(key)) {
                        Texture[][] arr = new Texture[2][pathsAttacks.get(key)[0].length];
                        for (int i = 0; i < pathsAttacks.get(key)[0].length; i++) {
                                arr[0][i] = new Texture();
                                arr[1][i] = new Texture();
                                try {
                                        arr[0][i].loadFromFile(Paths.get(pathsAttacks.get(key)[0][i]));
                                        arr[1][i].loadFromFile(Paths.get(pathsAttacks.get(key)[1][i]));
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        texturesAttacks.put(key, arr);
                }
                return texturesAttacks.get(key);
        }

        public Texture getIcon(String key) {
                if (!textureIcons.containsKey(key)) {
                        Texture t = new Texture();
                        try {
                                t.loadFromFile(Paths.get(pathsToIcon.get(key)));
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        textureIcons.put(key, t);
                }
                return textureIcons.get(key);
        }

        public Texture[] getProjectiles(String key) {
                if (!textureProjectiles.containsKey(key)) {
                        Texture[] t = new Texture[2];
                        t[0] = new Texture();
                        t[1] = new Texture();
                        try {
                                t[0].loadFromFile(Paths.get(pathsToProjectiles.get(key)[0]));
                                t[1].loadFromFile(Paths.get(pathsToProjectiles.get(key)[1]));
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        textureProjectiles.put(key, t);
                }
                return textureProjectiles.get(key);
        }

        public Texture getLoadingScreen(int frame) {
                if (frame < 0 || frame > pathsToLoadingScreen.length - 1) {
                        System.out.println("Wrong index");
                        return null;
                } else {
                        if (textureLoadingScreen[frame] == null) {
                                textureLoadingScreen[frame] = new Texture();
                                try {
                                        textureLoadingScreen[frame]
                                                        .loadFromFile(Paths.get(pathsToLoadingScreen[frame]));
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        return textureLoadingScreen[frame];
                }
        }

}

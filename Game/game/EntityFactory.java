import java.nio.file.Paths;
import java.util.HashMap;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 * Factory for entities Indexes: 101 main character, 150 - raider, 155 - boss1,
 * 201 - rolling ball, 202 Ogre
 */
public class EntityFactory {

        private HashMap<Integer, String[][][]> paths = new HashMap<>();
        private HashMap<Integer, Texture[][][]> textures = new HashMap<>();

        public EntityFactory() {
                paths.put(101, new String[][][] {
                                new String[][] { { "assets/Character/Character_Right1.png" },
                                                { "assets/Character/Character_Left1.png" } },
                                new String[][] { { "assets/Character/Character_Right1.png",
                                                "assets/Character/Character_Right2.png",
                                                "assets/Character/Character_Right3.png" },
                                                { "assets/Character/Character_Left1.png",
                                                                "assets/Character/Character_Left2.png",
                                                                "assets/Character/Character_Left3.png" } },
                                new String[][] { { "assets/Character/Character_Right1.png" },
                                                { "assets/Character/Character_Left1.png" } } });

                paths.put(102, new String[][][] {
                                new String[][] { { "assets/NPC/Allies/OrvilRight1.png" },
                                                { "assets/NPC/Allies/OrvilLeft1.png" } },
                                new String[][] { { "assets/NPC/Allies/OrvilRight1.png",
                                                "assets/NPC/Allies/OrvilRight2.png",
                                                "assets/NPC/Allies/OrvilRight1.png" },
                                                { "assets/NPC/Allies/OrvilLeft1.png",
                                                                "assets/NPC/Allies/OrvilLeft2.png",
                                                                "assets/NPC/Allies/OrvilLeft1.png" } },
                                new String[][] { { "assets/NPC/Allies/OrvilRight1.png" },
                                                { "assets/NPC/Allies/OrvilLeft1.png" } } });
                paths.put(103, new String[][][] {
                                new String[][] { { "assets/NPC/Allies/Wizard.png" },
                                                { "assets/NPC/Allies/Wizard.png" } },
                                new String[][] { { "assets/NPC/Allies/Wizard.png" },
                                                { "assets/NPC/Allies/Wizard.png" } },
                                new String[][] { { "assets/NPC/Allies/Wizard.png" },
                                                { "assets/NPC/Allies/Wizard.png" } } });
                paths.put(104, new String[][][] {
                                new String[][] { { "assets/NPC/Allies/Bartholomew_right.png" },
                                                { "assets/NPC/Allies/Bartholomew_right.png" } },
                                new String[][] { { "assets/NPC/Allies/Bartholomew_right.png" },
                                                { "assets/NPC/Allies/Bartholomew_right.png" } },
                                new String[][] { { "assets/NPC/Allies/Bartholomew_right.png" },
                                                { "assets/NPC/Allies/Bartholomew_right.png" } } });

                paths.put(150, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/Raider_Right1.png" },
                                                { "assets/NPC/Enemies/Raider_Left1.png" } },
                                new String[][] { { "assets/NPC/Enemies/Raider_Right1.png",
                                                "assets/NPC/Enemies/Raider_Right2.png",
                                                "assets/NPC/Enemies/Raider_Right3.png" },
                                                { "assets/NPC/Enemies/Raider_Left1.png",
                                                                "assets/NPC/Enemies/Raider_Left2.png",
                                                                "assets/NPC/Enemies/Raider_Left3.png" } },
                                new String[][] { { "assets/NPC/Enemies/Raider_Right1.png" },
                                                { "assets/NPC/Enemies/Raider_Left1.png" } } });

                paths.put(155, new String[][][] {
                                new String[][] { { "assets/Boss/Boss1_Right1.png", "assets/Boss/Boss1_Right.png",
                                                "assets/Boss/Boss1_Right2.png" },
                                                { "assets/Boss/Boss1_Left1.png", "assets/Boss/Boss1_Left.png",
                                                                "assets/Boss/Boss1_Left2.png" } },
                                new String[][] { { "assets/Boss/Boss1_Right1.png", "assets/Boss/Boss1_Right.png",
                                                "assets/Boss/Boss1_Right2.png" },
                                                { "assets/Boss/Boss1_Left1.png", "assets/Boss/Boss1_Left.png",
                                                                "assets/Boss/Boss1_Left2.png" } },
                                new String[][] { { "assets/Boss/Boss1_Right.png" },
                                                { "assets/Boss/Boss1_Left.png" } } });
                paths.put(201, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/rollingball_1.png" },
                                                { "assets/NPC/Enemies/rollingball_1.png" } },
                                new String[][] { { "assets/NPC/Enemies/rollingball_1.png",
                                                "assets/NPC/Enemies/rollingball_2.png",
                                                "assets/NPC/Enemies/rollingball_3.png",
                                                "assets/NPC/Enemies/rollingball_4.png" },
                                                { "assets/NPC/Enemies/rollingball_1.png",
                                                                "assets/NPC/Enemies/rollingball_2.png",
                                                                "assets/NPC/Enemies/rollingball_3.png",
                                                                "assets/NPC/Enemies/rollingball_4.png" } },
                                new String[][] { { "assets/NPC/Enemies/rollingball_1.png" },
                                                { "assets/NPC/Enemies/rollingball_1.png" } } });

                paths.put(202, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/OgreRight1.png" },
                                                { "assets/NPC/Enemies/OgreLeft1.png" } },
                                new String[][] { { "assets/NPC/Enemies/OgreRight1.png",
                                                "assets/NPC/Enemies/OgreRight1.png",
                                                "assets/NPC/Enemies/OgreRight1.png",
                                                "assets/NPC/Enemies/OgreRight2.png",
                                                "assets/NPC/Enemies/OgreRight2.png",
                                                "assets/NPC/Enemies/OgreRight2.png",
                                                "assets/NPC/Enemies/OgreRight1.png",
                                                "assets/NPC/Enemies/OgreRight1.png",
                                                "assets/NPC/Enemies/OgreRight4.png",
                                                "assets/NPC/Enemies/OgreRight4.png" },
                                                { "assets/NPC/Enemies/OgreLeft1.png",
                                                                "assets/NPC/Enemies/OgreLeft1.png",
                                                                "assets/NPC/Enemies/OgreLeft1.png",
                                                                "assets/NPC/Enemies/OgreLeft2.png",
                                                                "assets/NPC/Enemies/OgreLeft2.png",
                                                                "assets/NPC/Enemies/OgreLeft2.png",
                                                                "assets/NPC/Enemies/OgreLeft1.png",
                                                                "assets/NPC/Enemies/OgreLeft1.png",
                                                                "assets/NPC/Enemies/OgreLeft4.png",
                                                                "assets/NPC/Enemies/OgreLeft4.png", } },
                                new String[][] { { "assets/NPC/Enemies/OgreRight1.png" },
                                                { "assets/NPC/Enemies/OgreLeft1.png" } } });

                paths.put(203, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/GoblinRight1.png" },
                                                { "assets/NPC/Enemies/GoblinLeft1.png" } },
                                new String[][] { { "assets/NPC/Enemies/GoblinRight1.png",
                                                "assets/NPC/Enemies/GoblinRight1.png",
                                                "assets/NPC/Enemies/GoblinRight2.png",
                                                "assets/NPC/Enemies/GoblinRight2.png",
                                                "assets/NPC/Enemies/GoblinRight3.png",
                                                "assets/NPC/Enemies/GoblinRight3.png" },
                                                { "assets/NPC/Enemies/GoblinLeft1.png",
                                                                "assets/NPC/Enemies/GoblinLeft1.png",
                                                                "assets/NPC/Enemies/GoblinLeft2.png",
                                                                "assets/NPC/Enemies/GoblinLeft2.png",
                                                                "assets/NPC/Enemies/GoblinLeft3.png",
                                                                "assets/NPC/Enemies/GoblinLeft3.png" } },
                                new String[][] { { "assets/NPC/Enemies/GoblinRight1.png" },
                                                { "assets/NPC/Enemies/GoblinLeft1.png" } } });

                paths.put(205, new String[][][] { new String[][] {
                                { "assets/Tiles/new/lava1.png", "assets/Tiles/new/lava2.png",
                                                "assets/Tiles/new/lava3.png", "assets/Tiles/new/lava4.png" },
                                { "assets/Tiles/new/lava1.png", "assets/Tiles/new/lava2.png",
                                                "assets/Tiles/new/lava3.png", "assets/Tiles/new/lava4.png" } },
                                new String[][] { {}, {} }, new String[][] { {}, {} } });

                paths.put(206, new String[][][] { new String[][] {
                                { "assets/NPC/Enemies/DarkWarriorRight.png", "assets/NPC/Enemies/DarkWarriorRight1.png",
                                                "assets/NPC/Enemies/DarkWarriorRight2.png" },
                                { "assets/NPC/Enemies/DarkWarriorLeft.png", "assets/NPC/Enemies/DarkWarriorLeft1.png",
                                                "assets/NPC/Enemies/DarkWarriorLeft2.png" } },
                                new String[][] { { "assets/NPC/Enemies/DarkWarriorAttackRight.png",
                                                "assets/NPC/Enemies/DarkWarriorAttackRight1.png",
                                                "assets/NPC/Enemies/DarkWarriorAttackRight2.png",
                                                "assets/NPC/Enemies/DarkWarriorAttackRight1.png" },
                                                { "assets/NPC/Enemies/DarkWarriorAttackLeft.png",
                                                                "assets/NPC/Enemies/DarkWarriorAttackLeft1.png",
                                                                "assets/NPC/Enemies/DarkWarriorAttackLeft2.png",
                                                                "assets/NPC/Enemies/DarkWarriorAttackLeft1.png", } },
                                new String[][] { { "assets/NPC/Enemies/DarkWarriorAttackRight.png" },
                                                { "assets/NPC/Enemies/DarkWarriorAttackLeft.png" } } });

                paths.put(207, new String[][][] {
                                new String[][] { { "assets/Boss/Boss2_Right.png" }, { "assets/Boss/Boss2_Left.png" } },
                                new String[][] { { "assets/Boss/Boss2_Right1.png", "assets/Boss/Boss2_Right.png",
                                                "assets/Boss/Boss2_Right2.png" },
                                                { "assets/Boss/Boss2_Left1.png", "assets/Boss/Boss2_Left.png",
                                                                "assets/Boss/Boss2_Left2.png" } },
                                new String[][] { { "assets/Boss/Boss2_Right.png" },
                                                { "assets/Boss/Boss2_Left.png" } } });

                paths.put(209, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/Iceyrollingball_1.png" },
                                                { "assets/NPC/Enemies/Iceyrollingball_1.png" } },
                                new String[][] { { "assets/NPC/Enemies/Iceyrollingball_1.png",
                                                "assets/NPC/Enemies/Iceyrollingball_2.png",
                                                "assets/NPC/Enemies/Iceyrollingball_3.png",
                                                "assets/NPC/Enemies/Iceyrollingball_4.png" },
                                                { "assets/NPC/Enemies/Iceyrollingball_1.png",
                                                                "assets/NPC/Enemies/Iceyrollingball_2.png",
                                                                "assets/NPC/Enemies/Iceyrollingball_3.png",
                                                                "assets/NPC/Enemies/Iceyrollingball_4.png" } },
                                new String[][] { { "assets/NPC/Enemies/Iceyrollingball_1.png" },
                                                { "assets/NPC/Enemies/Iceyrollingball_1.png" } } });

                paths.put(210, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/SnowRaider_Right.png" },
                                                { "assets/NPC/Enemies/SnowRaider_Left.png" } },
                                new String[][] { { "assets/NPC/Enemies/SnowRaider_Right.png",
                                                "assets/NPC/Enemies/SnowRaider_Right1.png",
                                                "assets/NPC/Enemies/SnowRaider_Right2.png",
                                                "assets/NPC/Enemies/SnowRaider_Right1.png" },
                                                { "assets/NPC/Enemies/SnowRaider_Left.png",
                                                                "assets/NPC/Enemies/SnowRaider_Left1.png",
                                                                "assets/NPC/Enemies/SnowRaider_Left2.png",
                                                                "assets/NPC/Enemies/SnowRaider_Left1.png" } },
                                new String[][] { { "assets/NPC/Enemies/SnowRaider_Right.png" },
                                                { "assets/NPC/Enemies/SnowRaider_Left.png" } } });

                paths.put(208, new String[][][] {
                                new String[][] { { "assets/NPC/Enemies/SnowWarrior_Attack_Right.png" },
                                                { "assets/NPC/Enemies/SnowWarrior_Attack_Left.png" } },
                                new String[][] { { "assets/NPC/Enemies/SnowWarrior_Attack_Right.png" },
                                                { "assets/NPC/Enemies/SnowWarrior_Attack_Left.png" } },
                                new String[][] { { "assets/NPC/Enemies/SnowWarrior_Attack_Right.png",
                                                "assets/NPC/Enemies/SnowWarrior_Attack_Right1.png",
                                                "assets/NPC/Enemies/SnowWarrior_Attack_Right2.png",
                                                "assets/NPC/Enemies/SnowWarrior_Attack_Right1.png" },
                                                { "assets/NPC/Enemies/SnowWarrior_Attack_Left.png",
                                                                "assets/NPC/Enemies/SnowWarrior_Attack_Left1.png",
                                                                "assets/NPC/Enemies/SnowWarrior_Attack_Left2.png",
                                                                "assets/NPC/Enemies/SnowWarrior_Attack_Left1.png" } } });

                paths.put(211, new String[][][] {
                                new String[][] { { "assets/Boss/Boss3_Right.png" }, { "assets/Boss/Boss3_Left.png" } },
                                new String[][] { { "assets/Boss/Boss3_Right.png" }, { "assets/Boss/Boss3_Left.png" } },
                                new String[][] { { "assets/Boss/Boss3_Right.png", "assets/Boss/Boss3_Right1.png",
                                                "assets/Boss/Boss3_Right2.png", "assets/Boss/Boss3_Right1.png" },
                                                { "assets/Boss/Boss3_Left.png", "assets/Boss/Boss3_Left1.png",
                                                                "assets/Boss/Boss3_Left2.png",
                                                                "assets/Boss/Boss3_Left1.png" } } });
                paths.put(212, new String[][][] {
                                new String[][] { { "assets/Boss/Boss4_Right.png" }, { "assets/Boss/Boss4_Left.png" } },
                                new String[][] { { "assets/Boss/Boss4_Right.png" }, { "assets/Boss/Boss4_Left.png" } },
                                new String[][] { { "assets/Boss/Boss4_Right.png", "assets/Boss/Boss4_Right1.png",
                                                "assets/Boss/Boss4_Right2.png" },
                                                { "assets/Boss/Boss4_Left.png", "assets/Boss/Boss4_Left1.png",
                                                                "assets/Boss/Boss4_Left2.png", } } });

        }

        /**
         * Returns an entity defined by its id
         * 
         * @param pos position of the entity
         * @param id  id of the entity
         * @return new instance of the specified entity
         */
        public Entity getEntity(Vector2f pos, int id) {

                if (id == 101) {
                        initializeTexture(id);
                        return new MainCharacter(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 102) {
                        initializeTexture(id);
                        return new Orvil(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 103) {
                        initializeTexture(id);
                        return new WizardLvl2(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 104) {
                        initializeTexture(id);
                        return new Bartholomew(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 150) {
                        initializeTexture(id);
                        return new Raider(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 155) {
                        initializeTexture(id);
                        return new Boss1(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 201) {
                        initializeTexture(id);
                        return new RollingBall(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 202) {
                        initializeTexture(id);
                        return new Ogre(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                if (id == 203) {
                        initializeTexture(id);
                        return new Goblin(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 205) {
                        initializeTexture(id);
                        return new Lava(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 206) {
                        initializeTexture(id);
                        return new DarkWarrior(pos, textures.get(id)[0], textures.get(id)[2], textures.get(id)[1]);
                }
                if (id == 207) {
                        initializeTexture(id);
                        return new Boss2(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 208) {
                        initializeTexture(id);
                        return new SnowWarrior(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 209) {
                        initializeTexture(id);
                        return new SnowBall(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 210) {
                        initializeTexture(id);
                        return new SnowRaider(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 211) {
                        initializeTexture(id);
                        return new Boss3(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }
                if (id == 212) {
                        initializeTexture(212);
                        return new Boss4(pos, textures.get(id)[0], textures.get(id)[1], textures.get(id)[2]);
                }

                return null;
        }

        private void initializeTexture(int id) {
                if (!textures.containsKey(id)) {
                        Texture[][] idle = initializeOne(paths.get(id)[0]);
                        Texture[][] run = initializeOne(paths.get(id)[1]);
                        Texture[][] attack = initializeOne(paths.get(id)[2]);

                        textures.put(id, new Texture[][][] { idle, run, attack });
                }
        }

        private Texture[][] initializeOne(String[][] paths) {
                Texture[][] arr = new Texture[2][paths[0].length];

                for (int i = 0; i < paths[0].length; i++) {
                        arr[0][i] = new Texture();
                        arr[1][i] = new Texture();
                        try {
                                arr[0][i].loadFromFile(Paths.get(paths[0][i]));
                                arr[1][i].loadFromFile(Paths.get(paths[1][i]));
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                return arr;
        }
}

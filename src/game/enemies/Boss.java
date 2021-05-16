package game.enemies;

import java.util.Random;

public class Boss extends Enemy {
    private final EnemyProperty enemyProperty;

    public Boss(String name, int basicHealth,int basicMagic, int basicAttack, int basicDefence, int basicAvoidChance, int basicCritChance, int expGain, int skillChance) {
        this.enemyProperty = EnemyProperty.BOSS;
        this.setName(name);
        this.setHealthMax(basicHealth);
        this.setHealth(basicHealth);
        this.setMagicMax(basicMagic);
        this.setMagic(basicMagic);
        this.setAttack(basicAttack);// 10
        this.setDefence(basicDefence);// 5
        this.setAvoidChance(basicAvoidChance);// 1
        this.setCritChance(basicCritChance);// 1
        this.setExpGain(expGain);// 1
        this.setSkillChance(skillChance);
    }

    public EnemyProperty getEnemyProperty() {
        return enemyProperty;
    }
}

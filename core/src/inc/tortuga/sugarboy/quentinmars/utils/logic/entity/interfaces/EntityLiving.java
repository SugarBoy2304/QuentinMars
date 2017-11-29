package inc.tortuga.sugarboy.quentinmars.utils.logic.entity.interfaces;

import com.badlogic.gdx.utils.Array;

import inc.tortuga.sugarboy.quentinmars.utils.logic.items.Item;

/**
 * Created by swift on 27.11.2017.
 */

public interface EntityLiving extends Entity {

    /** Реализация движения
     *
     * Вернет True если движение возможно **/
    boolean up();

    boolean down();

    boolean left();

    boolean right();

    boolean action();

    /**
     * Наносит урон определенной цели
     *
     * @param entity нанесется урон этому существу
     * @param damage размер наносимого урона (неокончателбного)
     *
     * Вернет True если это возможно
     * **/
    boolean damageTo(Entity entity, float damage);

    /**
     * Преобразует базовый урон в конечный
     *
     * @param entity существо, которое наносит урон
     * @param damage размер первоначального урона
     *
     * Вернет значение конечного урона
     * **/
    float getFinalDamage(Entity entity, float damage);

    /**
     * Устанавливается дроп с существа после вызова метода die()
     *
     * @param items перечень предметов
     * **/
    void setDrop(Item... items);
    void setDrop(Array<Item> items);

    /**
     * Вернет True если существо не возраждаемо
     * **/
    boolean isRespawnable();

    /**
     * Вернет время в UNIX до следующего респавна
     * **/
    long getTimeToRespawn();

    /**
     * Получить Id существа
     * **/
    int getId();

    /**
     * Вернет количество жизней существа
     * **/
    float getHealth();

    /**
     * Отнимет количество жизней, если оно станет <= 0,
     * то вызовется метод die()
     * **/
    float removeHealth(float health);

    /**
     * Возвращается радиус видимости существа
     * **/
    int getRadiusVisible();

    /**
     * Возвращает угол обзора существа
     * **/
    int getDegreeVisible();

    int getDegreeVisibleAngle();

    /**
     * Может ли существо обновиться
     * */
    boolean isCanUpdate();

    /**
     * Смерть существа
     * **/
    void die();


}

package inc.tortuga.sugarboy.quentinmars.utils.logic.entity.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by swift on 27.11.2017.
 */

public interface Entity {

    /**
     * Установить позицию
     * **/
    void setPosition(int x, int y);

    /**
     * Получить позицию по оси X
     * **/
    int getX();

    /**
     * Получить позицию по оси Y
     * **/
    int getY();

    /**
     * @param sb - SpriteBatch необходимый для отрисовки объекта
     * важно учитывать то, что отрисовка должна зависить от **//* @param K *//**
     * который расположен в конфиге
     * **/
    void draw(SpriteBatch sb);

    /**
     * Прибавить к позиции аргумент, в основном необходим
     * для реализация движения некоторых объектов
     *
     * return true, если действие возмодно
     * **/
    boolean addPosition(int x, int y);

    /**
     * Полное уничтожение объекта из памяти игры
     * **/
    void dispose();

    /**
     * @param dt аргумент DeltaTime, который предпологает под
     * собой частату обновления кадров вашей игры
     * **/
    void update(double dt);

    /**
     * Если был вызван метод dispose, то должен вернуться true
     * **/
    boolean isDie();

}

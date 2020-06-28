package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class FruitManagerTest {

    List<Fruit> fruitList = new ArrayList<>();
    FruitManager fruitManager = new FruitManager();

    @Before
    public void before(){
        fruitList = fruitManager.getFruitList();
    }

    @Test
    public void getFruitCodesByID() {

        Assert.assertTrue(fruitManager.getFruitCodesByID("0").isEmpty());
        Assert.assertFalse(fruitManager.getFruitCodesByID("1").isEmpty());
        Assert.assertFalse(fruitManager.getFruitCodesByID("2").isEmpty());
        Assert.assertFalse(fruitManager.getFruitCodesByID("3").isEmpty());
        Assert.assertFalse(fruitManager.getFruitCodesByID("4").isEmpty());
        Assert.assertTrue(fruitManager.getFruitCodesByID("5").isEmpty());

    }

    @Test
    public void getFruitList() {

        Assert.assertNotNull(fruitList);
        Assert.assertEquals(fruitList.size(),4);
        Assert.assertEquals(fruitList.get(0).getCode(), "ST");
        Assert.assertEquals(fruitList.get(1).getCode(), "RA");
        Assert.assertEquals(fruitList.get(2).getCode(), "BL");
        Assert.assertEquals(fruitList.get(3).getCode(), "GO");

    }

    @Test
    public void getFruitNameByCode() {

        Assert.assertEquals(fruitManager.getFruitNameByCode(""), "null");
        Assert.assertFalse(fruitManager.getFruitNameByCode("ST").isEmpty());
        Assert.assertFalse(fruitManager.getFruitNameByCode("RA").isEmpty());
        Assert.assertFalse(fruitManager.getFruitNameByCode("BL").isEmpty());
        Assert.assertFalse(fruitManager.getFruitNameByCode("GO").isEmpty());
        Assert.assertEquals(fruitManager.getFruitNameByCode("z"), "null");

    }

    @After
    public void after(){
        fruitList = null;
        fruitManager = null;
    }
}
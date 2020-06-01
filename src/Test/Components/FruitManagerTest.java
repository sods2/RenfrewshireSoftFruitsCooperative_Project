package Test.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertNotNull(fruitManager.getFruitCodesByID("1"));
        Assert.assertNotNull(fruitManager.getFruitCodesByID("2"));
        Assert.assertNotNull(fruitManager.getFruitCodesByID("3"));
        Assert.assertNotNull(fruitManager.getFruitCodesByID("4"));
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
        Assert.assertNotNull(fruitManager.getFruitNameByCode("ST"));
        Assert.assertNotNull(fruitManager.getFruitNameByCode("RA"));
        Assert.assertNotNull(fruitManager.getFruitNameByCode("BL"));
        Assert.assertNotNull(fruitManager.getFruitNameByCode("GO"));
        Assert.assertEquals(fruitManager.getFruitNameByCode("z"), "null");

    }

    @After
    public void after(){
        fruitList = null;
        fruitManager = null;
    }
}
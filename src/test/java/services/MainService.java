package services;

import org.testng.Assert;

import java.util.List;

/**
 * There are declared commonly used methods
 */
public class MainService {

    public static void compareTwoLists(List<String> listAct, List<String> listExp) {
        Assert.assertEquals(listAct.size(), listExp.size(), "Lists size don't match!");
        Assert.assertTrue(listAct.containsAll(listExp), "Goods don't match!");
    }

}

package pages;


import static core.helpers.ActionHelper.checkThatAllElementsContainsText;

public class NoteBooksPage extends AbstractPage {

    public boolean checkThatGoodsTileContainsSearchWord(String searchWord) {
        getGoodItemFragment().witForAllGoodsBodyItemsArePresent();
        return checkThatAllElementsContainsText(getGoodItemFragment().getGoodsTitle(), searchWord);
    }

    public void checkThatGoodsItemsHaveSize(int size) {
        getGoodItemFragment().checkSizeAllGoodsBodyItems(size);
    }
}
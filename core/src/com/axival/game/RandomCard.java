package com.axival.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.*;

public class RandomCard{
    private int randResult;
    private CardPlay cardPlay;
    private TextureAtlas cardAll;
    private HorizontalGroup hand;
    private Image[] cardPack;
    private Stage stage;
    private int[] idenCardAll;

    private TextureRegionDrawable textureRegionDrawable;
    private Image image;

    private Map<Integer, HashMap<Integer, Integer>> cardDictionary;

    private Map<String,Integer> limitCard;

    private ArrayList<String> countCardInHand;

    private int[] countCardGenLimit;
    private int numGenLimit;
    private List<Integer> collectLimit;
    public RandomCard(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.cardAll = cardPlay.assetManager.get("cardani/spritesheet/cardAni.atlas", TextureAtlas.class);
        this.countCardInHand = new ArrayList<String>();
        this.cardDictionary = new HashMap<Integer, HashMap<Integer, Integer>>();
        this.limitCard = new HashMap<String, Integer>();

        setCardDictionary(0,0,2);
        setCardDictionary(1, 0, 6);
        setCardDictionary(2, 1, 3);
        setCardDictionary(3, 2, 1);
        setCardDictionary(4, 3, 0);
        setCardDictionary(5, 4, 2);
        setCardDictionary(6, 5, 2);
        setCardDictionary(7, 6, 10);

        setLimitCard();
        generateRandomLimit();
    }
    public int generateRandom(int first, int last, int index){
//        Random rand = new Random();
//        randResult = rand.nextInt(last-first+1)+first;
//        System.out.print(randResult);
        randResult = collectLimit.get(index);
        return randResult;
    }
    public Image getCard(int randResult)
    {
        textureRegionDrawable = new TextureRegionDrawable(cardAll.findRegion(String.format("%03d", randResult)));
        image = new Image(textureRegionDrawable);
        //image.getImage().setFillParent(true);
        return image;
    }

    public Image[] allCardDeck(int maxCard){
        Image[] cardDeck = new Image[maxCard];
        idenCardAll = new int[maxCard];
        for(int i=0;i<maxCard;i++){
            randResult = generateRandom(1,7, i);
            cardDeck[i] = getCard(randResult);
            cardDeck[i].setScale(.05f);
            cardDeck[i].setPosition(640-cardDeck[i].getWidth()/10, 700);
            idenCardAll[i] = randResult;
        }
        return cardDeck;
    }

    public void setCardInHandIndex(int indexCard){
        countCardInHand.add(indexCard+"");
        System.out.println("Set:"+getCountCardInHand());
        System.out.println("Set(status):"+ Arrays.toString(getIdenCardAll()));
    }

    public void removeCardInHandIndex(int valueCard){
        countCardInHand.remove(valueCard);
        System.out.println("Remove:"+getCountCardInHand());
        System.out.println("Remove(status):"+Arrays.toString(getIdenCardAll()));
    }

    public ArrayList<String> getCountCardInHand() {
        return countCardInHand;
    }

    public int[] getIdenCardAll(){
        return idenCardAll;
    }

    public int sizeCountCardInHand(){
        return countCardInHand.size();
    }

    public void setCardDictionary(int idCard, int typeCard, int value){
        if(cardDictionary.get(idCard)==null){
            cardDictionary.put(idCard, new HashMap<Integer, Integer>());
            cardDictionary.get(idCard).put(typeCard, value);
        }
        else{
            cardDictionary.get(idCard).put(typeCard, value);
        }
        System.out.println(cardDictionary+"");
    }

    public void setLimitCard(){
        // (idCard, amountInDeck)
        limitCard.put("0", 6);
        limitCard.put("1", 2);
        limitCard.put("2", 4);
        limitCard.put("3", 3);
        limitCard.put("4", 1);
        limitCard.put("5", 3);
        limitCard.put("6", 3);
        limitCard.put("7", 1);
    }

    public void generateRandomLimit(){
//        countCardGenLimit = new int[23];
//        int count = 0;
//        while(count<23){
//            numGenLimit = generateRandom(1, 7);
//            if (countInArray(countCardGenLimit, numGenLimit) < limitCard.get(numGenLimit+"")) {
//                countCardGenLimit[count] = numGenLimit;
//                count++;
//            }
//        }
//        System.out.println(countCardGenLimit+"");
        collectLimit = new ArrayList<Integer>();
        for(int i=0;i<=7;i++) {
            addCollectAllCard(i);
        }
        Collections.shuffle(collectLimit);
        System.out.println(collectLimit+"");
    }

    public int countInArray(int[] numArray, int numCheck){
        int countArray = 0;
        for(int i=0;i<numArray.length;i++){
            if(numArray[i]==numCheck){
                countArray += 1;
            }
        }
        return countArray;
    }

    public void addCollectAllCard(int num){
        for(int i=0; i<limitCard.get(num+"");i++){
            collectLimit.add(num);
        }
    }
}


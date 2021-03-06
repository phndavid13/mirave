package com.master.ndavid.calirapido;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.David on 18/09/2015.
 */
public class Trivia {

    public static final int TOTAL_PREGS=5;

    private ArrayList<Question> questions;
    private DBHelper dbHelper;
    private double puntajeTotal;
    private int puntajeBien;
    public Trivia(){
        dbHelper = new DBHelper();
        questions = new ArrayList<Question>();
        puntajeTotal = new Double(0.0);
        puntajeBien=0;
        createQuestion();
    }

    public  void createQuestion(){
        ArrayList<String[]> trivias = dbHelper.getTrivias();
        for (int i = 0; i < trivias.size(); i++) {
            String[] trivia = trivias.get(i);
            ArrayList<Answer> optionsAnswer = new ArrayList<Answer>();
            optionsAnswer.add(new Answer(trivia[3],true));
            optionsAnswer.add(new Answer(trivia[4],false));
            optionsAnswer.add(new Answer(trivia[5],false));
            optionsAnswer.add(new Answer(trivia[6],false));
            Question question = new Question(Integer.parseInt(trivia[0]),trivia[1],trivia[2],optionsAnswer,trivia[7],trivia[8]);
            questions.add(question);
        }
    }

    public ArrayList<Question> getFiambreQuestions(){
        ArrayList<Question> fiambreQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size()-1;i++){
            if(questions.get(i).getType().equals("Fiambre")){
                fiambreQuestions.add(questions.get(i));
            }
        }
        return fiambreQuestions;
    }
    public ArrayList<Question> getGuanguancoQuestions(){
        ArrayList<Question> guaguancoQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getType().equals("Guaguanco")){
                guaguancoQuestions.add(questions.get(i));
            }
        }
        return guaguancoQuestions;
    }
    public ArrayList<Question> getBorondoQuestions(){
        ArrayList<Question> borondoQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getType().equals("Pa pegarnos el borondo")){
                borondoQuestions.add(questions.get(i));
            }
        }
        return borondoQuestions;
    }
    public ArrayList<Question> getCulturaQuestions(){
        ArrayList<Question> culturaQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getType().equals("Cultura")){
                culturaQuestions.add(questions.get(i));
            }
        }
        return culturaQuestions;
    }
    public ArrayList<Question> getTipicasQuestions(){
        ArrayList<Question> tipicasQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getType().equals("Palabras tipicas")){
                tipicasQuestions.add(questions.get(i));
            }
        }
        return tipicasQuestions;
    }
    public ArrayList<Question> getOnePlay(){
        ArrayList<Question> onePlay = new ArrayList<Question>();

        double randomEating = Math.random()*4;
        double randomMusic = Math.random()*4;
        double randomTuristicos = Math.random()*4;
        double randomCultura = Math.random()*4;
        double randomTipicas = Math.random()*4;

        int eating = (int) randomEating;
        int music = (int) randomMusic;
        int turisticos = (int) randomTuristicos;
        int cultura = (int) randomCultura;
        int tipicas = (int) randomTipicas;

        Question theEating = getFiambreQuestions().get(eating);

        Question theMusic = getGuanguancoQuestions().get(music);
        Question theTuristicos = getBorondoQuestions().get(turisticos);
        Question theCultura = getCulturaQuestions().get(cultura);
        Question theTipicas = getTipicasQuestions().get(tipicas);

        onePlay.add(theEating);
        onePlay.add(theMusic);
        onePlay.add(theTuristicos);
        onePlay.add(theCultura);
        onePlay.add(theTipicas);

        return onePlay;
    }
    public void preguntaBuena(){
        puntajeBien++;
        System.out.println("Puntaje Bien: " + puntajeBien);
    }
    public void calcularPorcentajePartida(){
        //double punt = new Double(puntajeBien)/TOTAL_PREGS*100;
        puntajeTotal = (new Double(puntajeBien)/TOTAL_PREGS)*100;
        //System.out.println("puntajeparcial en el calculo: " + punt);
        System.out.println("puntajeBien en el calculo: " + puntajeBien);
        System.out.println("TOTALPREGS: " + TOTAL_PREGS);
        System.out.println("puntajeTotal en el calculo: " + puntajeTotal);
    }
    public double getPuntajeTotal(){
        System.out.println("PuntajeTotal: " + puntajeTotal);
        return puntajeTotal;
    }

}

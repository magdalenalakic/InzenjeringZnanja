package controller;

import model.RezPritiskaEnum;

public class PacijentController {
    public PacijentController() { }

    public RezPritiskaEnum racunanjeRezultataPritiska(Integer gornjiPritisak, Integer donjiPritisak){
        if(gornjiPritisak > 120 && donjiPritisak > 80){
            return RezPritiskaEnum.povisen;
        }else if(gornjiPritisak < 100 && donjiPritisak < 60){
            return RezPritiskaEnum.nizak;
        }else if(gornjiPritisak >= 100 && gornjiPritisak <= 120 && donjiPritisak >= 60 && donjiPritisak <= 80){
            return RezPritiskaEnum.normalan;
        }
        return RezPritiskaEnum.normalan;
    }
}

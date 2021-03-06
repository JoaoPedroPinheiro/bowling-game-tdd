public class Game {

    private int frame;
    private int frameRoll;
    private int score;
    private int frameScore;
    private boolean spareBonus;
    private int strikeBonus = 0;
    private boolean isOver;

    public void roll(int pins) {
        frameScore +=pins;

        if(frameScore > 10) {
            throw new IllegalArgumentException();
        } else if (frameEnded()){
            endFrame();
        } else {
            frameRoll++;
        }
    }

    private void endFrame() {
        calculateScore();
        calculateBonus();
        updateCounters();
    }

    private void calculateBonus() {
        if(frameScore == 10){
            if(frameRoll == 0){
                strikeBonus = 2;
            } else {
                spareBonus = true;
            }
        }
    }

    private boolean frameEnded() {
        return frameScore == 10 ||
                frameRoll ==1;
    }

    private void calculateScore() {
        score += frameScore;
        if(spareBonus){
            score += frameScore;
        } if (strikeBonus > 0){
            score += frameScore;
            strikeBonus--;
        }
    }

    private void updateCounters() {
        frameRoll = 0;
        frameScore =0;
        frame++;
        if(frame ==10)
            isOver=true;
    }

    public int getFrame() {
        return frame;
    }

    public int getScore() {
        return score;
    }

    public boolean isOver() {
        return isOver;
    }
}

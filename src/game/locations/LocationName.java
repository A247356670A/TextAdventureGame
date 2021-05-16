package game.locations;

import java.util.Arrays;
import java.util.LinkedList;
// The thesaurus of map name. A battle name include the heading(represent the risk level) and Landscape.

public class LocationName {

    LinkedList<String> riskLevel;
    LinkedList<String> landscape;

    private static String[] setSafe(){
        String[] safe = new String[5];
        Arrays.fill(safe, "Safe");
        return safe;
    }

    private static String[] setNeutral(){
        String[] neutral = new String[10];
        Arrays.fill(neutral,"Neutral");
        return neutral;
    }

    private static String[] setPeaceful(){
        String[] peaceful = new String[5];
        Arrays.fill(peaceful,"Peaceful");
        return peaceful;
    }

    private static String[] setDisturbed(){
        String[] disturbed = new String[10];
        Arrays.fill(disturbed, "Disturbed");
        return disturbed;
    }

    private static String[] setDangerous(){
        String[] dangerous = new String[10];
        Arrays.fill(dangerous,"Dangerous");
        return dangerous;
    }


    private static String[] setRiver(){
        String[] river = new String[3];
        Arrays.fill(river,"River");
        return river;
    }

    private static String[] setLagoon(){
        String[] lagoon = new String[2];
        Arrays.fill(lagoon,"Lagoon");
        return lagoon;
    }

    private static String[] setDesert(){
        String[] desert = new String[2];
        Arrays.fill(desert,"Desert");
        return desert;
    }

    private static String[] setLake(){
        String[] lake = new String[2];
        Arrays.fill(lake, "Lake");
        return lake;
    }

    private static String[] setGrassland(){
        String[] grassland = new String[3];
        Arrays.fill(grassland,"Grassland");
        return grassland;
    }

    private static String[] setCamp(){
        String[] camp = new String[5];
        Arrays.fill(camp,"Camp");
        return camp;
    }

    private static String[] setShrub(){
        String[] shrub = new String[3];
        Arrays.fill(shrub,"Shrub");
        return shrub;
    }

    private static String[] setForest(){
        String[] forest = new String[3];
        Arrays.fill(forest,"Forest");
        return forest;
    }

    private static String[] setBeach(){
        String[] beach = new String[3];
        Arrays.fill(beach,"Beach");
        return beach;
    }

    private static String[] setHill() {
        String[] hill = new String[2];
        Arrays.fill(hill,"Hill");
        return hill;
    }

    private static String[] setMountain(){
        String[] mountain = new String[2];
        Arrays.fill(mountain,"Mountain");
        return mountain;
    }

    public void setRiskLevel() {
        this.riskLevel = new LinkedList<>();
        riskLevel.addAll(Arrays.asList(setSafe()));
        riskLevel.addAll(Arrays.asList(setDisturbed()));
        riskLevel.addAll(Arrays.asList(setPeaceful()));
        riskLevel.addAll(Arrays.asList(setDangerous()));
        riskLevel.addAll(Arrays.asList(setNeutral()));
        //Total amount:40, Type:5
        //RiskLevel: Safe and peaceful: low battle probability， Neutral: medium probability， Disturbed and Dangerous: high battle probability
    }

    public void setLandscape(){
        this.landscape = new LinkedList<>();
        landscape.addAll(Arrays.asList(setRiver()));
        landscape.addAll(Arrays.asList(setLagoon()));
        landscape.addAll(Arrays.asList(setLake()));
        landscape.addAll(Arrays.asList(setCamp()));
        landscape.addAll(Arrays.asList(setBeach()));
        landscape.addAll(Arrays.asList(setForest()));
        landscape.addAll(Arrays.asList(setMountain()));
        landscape.addAll(Arrays.asList(setHill()));
        landscape.addAll(Arrays.asList(setShrub()));
        landscape.addAll(Arrays.asList(setDesert()));
        landscape.addAll(Arrays.asList(setGrassland()));
        //Total: 30, Type: 11
        //Camp: increase HP
    }
}

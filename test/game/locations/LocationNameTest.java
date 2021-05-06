package game.locations;

import org.junit.Test;

public class LocationNameTest {

    LocationName locationName = new LocationName();

    @Test
    public void riskLevelAmount(){
        locationName.setRiskLevel();
        System.out.println(locationName.riskLevel.size());

    }

    @Test
    public void riskLevelTypeAmount(){
        locationName.setRiskLevel();
        int countSafe = 0;
        int countDangerous = 0;
        int countDisturbed = 0;
        int countPeaceful = 0;
        int countNeutral = 0;
        for(int i = 0; i < locationName.riskLevel.size(); i++){
            if(locationName.riskLevel.get(i).equals("Safe")){
                countSafe++;
            }
            if(locationName.riskLevel.get(i).equals("Neutral")){
                countNeutral++;
            }
            if(locationName.riskLevel.get(i).equals("Peaceful")){
                countPeaceful++;
            }
            if(locationName.riskLevel.get(i).equals("Disturbed")){
                countDisturbed++;
            }
            if(locationName.riskLevel.get(i).equals("Dangerous")){
                countDangerous++;
            }
        }
        System.out.println("Safe has " + countSafe);
        System.out.println("Neutral has " + countNeutral);
        System.out.println("Peaceful has " + countPeaceful);
        System.out.println("Disturbed has " + countDisturbed);
        System.out.println("Dangerous has " + countDangerous);
    }

    @Test
    public void landscapeAmount(){
        locationName.setLandscape();
        System.out.println(locationName.landscape.size());
    }

    @Test
    public void trueLandscapeTypeAmount(){
        locationName.setLandscape();
        int countCamp = 0;
        for(int i = 0; i < locationName.landscape.size(); i++){
            if(locationName.landscape.get(i).equals("Camp")){
                countCamp++;
            }
        }
        System.out.println(countCamp);

    }
}

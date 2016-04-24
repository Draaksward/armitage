package org.armitage.inc.AAInfo.setup;

import java.util.ArrayList;
import java.util.List;

import org.armitage.inc.AAInfo.dao.LocationRepository;
import org.armitage.inc.AAInfo.dao.UserRepository;
import org.armitage.inc.AAInfo.entity.Location;
import org.armitage.inc.AAInfo.entity.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFiller {
    @Autowired
    private Logger logger;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Bean
    public Void fillInData(){
        if(userRepository.count() < 1){
            logger.info("Creating admin account");
            User user = new User();
            user.setUserName("admin");
            user.setPassword("$2a$10$GCO0J2jn4nIfaPO8WzcHuuUrnaV.U/vDlUGOzdfpdrq1mB/3vV/mK");
            user.setEnabled(1);
            logger.info("saving");
            userRepository.save(user);
            logger.info("done");
        }
        
        if(locationRepository.count() < 1){
            logger.info("location repository is empty. Filling with data");
            List<Location> locations = new ArrayList<Location>();
            //public Location(String locationName, Integer isMerchant) {
            Location location = new Location("Мерианхольд",1);
            locations.add(location);
            location = new Location("Лес Гвинедар", 1);
            locations.add(location);
            location = new Location("Заболоченные низины", 0);
            locations.add(location);
            location = new Location("Золотые равнины", 0);
            locations.add(location);
            location = new Location("Две Короны", 1);
            locations.add(location);
            location = new Location("Земля Говорящих Камней", 0);
            locations.add(location);
            location = new Location("Полуосторов Падающих Звезд", 1);
            locations.add(location);
            location = new Location("Полуостров Солрид", 1);
            locations.add(location);
            location = new Location("Белый лес", 1);
            locations.add(location);
            logger.info("saving");
            locationRepository.save(locations);
            logger.info("done");
        }
        
        
        return null;
    }
}
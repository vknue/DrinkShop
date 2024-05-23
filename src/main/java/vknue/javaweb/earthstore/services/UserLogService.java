package vknue.javaweb.earthstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vknue.javaweb.earthstore.models.UserLog;
import vknue.javaweb.earthstore.models.enums.EventType;
import vknue.javaweb.earthstore.repositories.IUserLogRepository;

import java.time.Instant;
import java.util.List;

@Service
public class UserLogService {

    @Autowired
    private IUserLogRepository userLogRepository;

    @Autowired
    UserLogService(IUserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    public void logUserEvent(String name, String ipAddress, EventType eventType) {
        UserLog userLog = new UserLog();
        userLog.setName(name);
        userLog.setIpAddress(ipAddress);
        userLog.setLoginDate(Instant.now());
        userLog.setEventType(eventType);
        userLogRepository.save(userLog);
    }

    public List<UserLog> getAllLogs() {
        return userLogRepository.findAll();
    }
}
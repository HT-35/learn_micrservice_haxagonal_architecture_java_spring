package org.example.domain.ports;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.track.TrackOrderQuery;
import org.example.domain.dto.track.TrackOrderResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderTrackCommandHandler {
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}

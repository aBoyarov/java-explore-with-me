package exploreWithMe.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author Andrey Boyarov
 */

@Getter @Setter @ToString
@Builder
public class Location {

    private Double lat;

    private Double lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(lat, location.lat) && Objects.equals(lon, location.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}

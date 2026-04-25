package group1.tutoringcenter;

import group1.tutoringcenter.models.Request;
import group1.tutoringcenter.models.Tutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutoringCenterService {

    private List<Request> requests = new ArrayList<>();
    private List<Tutor> tutors = new ArrayList<>();


    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        requests.add(request);
    }


    public List<Tutor> getTutors() {
        return tutors;
    }

    public void addTutor(Tutor tutor) {
        tutors.add(tutor);
    }
}
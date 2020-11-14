package io.swagger.api;

import io.swagger.model.Challenge;

import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Goal;
import io.swagger.realityfamily.Repositories.ChallengeRepository;
import io.swagger.realityfamily.Repositories.ClientsRepository;
import io.swagger.realityfamily.Repositories.GoalsRepository;
import io.swagger.realityfamily.Repositories.PatternsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-07T19:30:51.910Z[GMT]")
@Controller
public class ChallengeApiController implements ChallengeApi {

    private static final Logger log = LoggerFactory.getLogger(ChallengeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private GoalsRepository goalsRepository;
    @Autowired
    private PatternsRepository patternsRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public ChallengeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Challenge> getChallenge(@ApiParam(value = "", required=true) @PathVariable("challengeId") UUID challengeId,
                                                  @ApiParam(value = "" ) @RequestHeader(value="Auth", required=false) String auth
) throws UnsupportedEncodingException {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            List<Challenge> challenges = challengeRepository.findAll();
            for (Challenge challenge : challenges) {
                if (challenge.getId().equals(challengeId)) {
                    return new ResponseEntity<Challenge>(challenge, HttpStatus.OK);
                }
            }
            return new ResponseEntity<Challenge>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Challenge>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> postChallenge(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Challenge body,
                                              @ApiParam(value = "" ) @RequestHeader(value="Auth", required=false) String auth) {

        if(body != null){
            Challenge challenge = body;
            if(body.getGoal() != null){
                Goal goal = body.getGoal();

                //checking if goal exist
                String goalName =  goal.getName();
                Goal instance = goalsRepository.findByName(goalName);
                if(goal.getName().equals(instance.getName()))
                    //goal = instance;
                    goal.setName("UndefinedGoal"+(int)Math.random()*1000);

                // setting goal challenge in goal and goal in challenge
                goal.setChallenge(challenge);
                challenge.setGoal(goal);
                //goalsRepository.save(goal);
                challengeRepository.save(challenge);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteChallenge(@ApiParam(value = "" ,required=true )  @PathVariable("deleteId") UUID body,
                                                @ApiParam(value = "" ) @RequestHeader(value="Auth", required=false) String auth) {
        challengeRepository.delete(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}

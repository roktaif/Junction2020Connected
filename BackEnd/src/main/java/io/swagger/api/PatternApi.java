/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.22).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Pattern;
import java.util.UUID;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-11-07T19:30:51.910Z[GMT]")
@Api(value = "pattern", description = "the pattern API")
public interface PatternApi {

    @ApiOperation(value = "", nickname = "getPattern", notes = "", response = Pattern.class, tags={ "pattern", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Pattern.class),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/pattern/{patternId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Pattern> getPattern(@ApiParam(value = "",required=true) @PathVariable("patternId") UUID patternId
,@ApiParam(value = "" ) @RequestHeader(value="Auth", required=false) String auth
);


    @ApiOperation(value = "", nickname = "postPattern", notes = "", tags={ "pattern", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Authentication information is missing or invalid") })
    @RequestMapping(value = "/pattern",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> postPattern(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Pattern body
,@ApiParam(value = "" ) @RequestHeader(value="Auth", required=false) String auth
);

}

package com.spring.delivery.service.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.spring.delivery.domain.request.HLSCloudinaryRequest;
import com.spring.delivery.domain.response.HLSCloudinaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryImpl implements CloudinaryService {
    Cloudinary cloudinary;
    @Value("${app.service.cloudinary.folder}")
    @NonFinal
    String rootFolder;

    @Override
    public HLSCloudinaryResponse uploadMp3(HLSCloudinaryRequest hlsCloudinaryRequest) throws Exception {
        String publicId = rootFolder + "/" + hlsCloudinaryRequest.getParent() + "/" + hlsCloudinaryRequest.getName();
        // Define the eager transformations using Transformation objects
        Transformation transformation = new Transformation()
                .streamingProfile("hd");
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "resource_type", "video",
                "public_id", publicId,
                "eager", Arrays.asList(transformation),
                "eager_async", true
        );

        Map uploadResult = cloudinary.uploader().upload(hlsCloudinaryRequest.getMp3().getBytes(), uploadParams);

        // Retrieve the HLS URL from the eager transformation result
        if (uploadResult.containsKey("eager")) {
            String urlOriginal = (String) uploadResult.get("url");
            String secureUrlOriginal = (String) uploadResult.get("secure_url");
            Map eagerResult = ((Map[]) uploadResult.get("eager"))[0];
            String urlM3u4 = (String) eagerResult.get("url");
            String secureUrlM3u4 = (String) eagerResult.get("secure_url");
            Instant createdAt = Instant.parse((String) uploadResult.get("created_at"));
            return HLSCloudinaryResponse.builder()
                    .secureUrlM3u4(secureUrlM3u4)
                    .urlM3u4(urlM3u4)
                    .secureUrlOriginal(secureUrlOriginal)
                    .urlOriginal(urlOriginal)
                    .createdAt(createdAt)
                    .build();
        }
        return null;
    }
}

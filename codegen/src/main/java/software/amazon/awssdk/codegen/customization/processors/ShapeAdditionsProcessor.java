/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.codegen.customization.processors;

import java.util.Map;
import software.amazon.awssdk.codegen.customization.CodegenCustomizationProcessor;
import software.amazon.awssdk.codegen.model.config.customization.ShapeAdditions;
import software.amazon.awssdk.codegen.model.intermediate.IntermediateModel;
import software.amazon.awssdk.codegen.model.service.ServiceModel;
import software.amazon.awssdk.codegen.model.service.Shape;

public class ShapeAdditionsProcessor implements CodegenCustomizationProcessor {

    private final ShapeAdditions shapeAdditions;

    ShapeAdditionsProcessor(ShapeAdditions shapeAdditions) {
        this.shapeAdditions = shapeAdditions;
    }

    @Override
    public void preprocess(ServiceModel serviceModel) {
        if (shapeAdditions == null) {
            return;
        }
        Map<String, Shape> shapes = serviceModel.getShapes();
        for (String shapeName: shapeAdditions.getShapes().keySet()) {
            shapes.put(shapeName, shapeAdditions.getShape(shapeName));
        }
        serviceModel.setShapes(shapes);
    }

    @Override
    public void postprocess(IntermediateModel intermediateModel) {

    }
}

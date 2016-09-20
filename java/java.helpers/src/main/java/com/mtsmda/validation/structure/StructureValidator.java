package com.mtsmda.validation.structure;

import com.mtsmda.helper.ObjectHelper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by MTSMDA on 31.08.2016.
 */
public class StructureValidator<T> {

    private ValidatorFactory validatorFactory;

    public StructureValidationResult validate(T object, Class<?> ... groups) {
        StructureValidationResult structureValidationResult = null;
        Set<ConstraintViolation<T>> constraintViolations =
                getValidatorFactory().getValidator().validate(object, groups);
        if (ObjectHelper.objectIsNotNull(constraintViolations) && constraintViolations.isEmpty()) {
            structureValidationResult = new StructureValidationResult(true, null);
        } else {
            structureValidationResult = new StructureValidationResult(false, constraintViolations);
        }
        return structureValidationResult;
    }

    public ValidatorFactory getValidatorFactory() {
        if(ObjectHelper.objectIsNull(validatorFactory)){
            validatorFactory = Validation.buildDefaultValidatorFactory();
        }
        return validatorFactory;
    }

    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public class StructureValidationResult {
        private Boolean successValidation;
        private Set<ConstraintViolation<T>> constraintViolations = null;

        public StructureValidationResult(Boolean successValidation, Set<ConstraintViolation<T>> constraintViolations) {
            this.successValidation = successValidation;
            this.constraintViolations = constraintViolations;
        }

        public Boolean getSuccessValidation() {
            return successValidation;
        }

        public void setSuccessValidation(Boolean successValidation) {
            this.successValidation = successValidation;
        }

        public Set<ConstraintViolation<T>> getConstraintViolations() {
            return constraintViolations;
        }

        public void setConstraintViolations(Set<ConstraintViolation<T>> constraintViolations) {
            this.constraintViolations = constraintViolations;
        }

        private String getStringMessageProcess(int type){
            StringBuilder stringBuilder = null;
            if(!this.getSuccessValidation() && ObjectHelper.objectIsNotNull(this.getConstraintViolations())){
                stringBuilder = new StringBuilder("Error path:\r\n");
                Iterator<ConstraintViolation<T>> iterator = this.getConstraintViolations().iterator();
                while (iterator.hasNext()){
                    ConstraintViolation<T> next = iterator.next();
                    stringBuilder.append(next.getPropertyPath());
                    if(type == 0){
                        stringBuilder.append(" - ").append(next.getMessage());
                    }
                    stringBuilder.append(";\r\n");

                }
            }
            return stringBuilder.toString();
        }

        public String getStringMessageForLogger(){
            return getStringMessageProcess(0);
        }

        public String getStringMessage(){
            return getStringMessageProcess(-1);
        }

    }

}
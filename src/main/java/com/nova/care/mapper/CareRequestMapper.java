package com.nova.care.mapper;

import com.nova.care.dto.InsurancePlanDto;
import com.nova.care.model.InsurancePlan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CareRequestMapper {

    public InsurancePlanDto toPlanDto(InsurancePlan plan) {
        if (plan == null) {
            return null;
        }
        return new InsurancePlanDto(
                plan.getPlanId(),
                plan.getProvider(),
                plan.getMemberId(),
                plan.getType(),
                plan.getGroupNumber()
        );
    }

    public List<InsurancePlanDto> toPlanDtos(List<InsurancePlan> plans) {
        if (plans == null) {
            return List.of();
        }
        return plans.stream().map(this::toPlanDto).collect(Collectors.toList());
    }
}

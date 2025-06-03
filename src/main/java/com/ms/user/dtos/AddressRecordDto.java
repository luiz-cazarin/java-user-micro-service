package com.ms.user.dtos;

import java.util.UUID;

public record AddressRecordDto (String address,
                                String city,
                                String state,
                                String zip) {
}

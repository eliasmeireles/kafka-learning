package com.eliasmeireles.kafkalearning.mapper

import com.eliasmeireles.kafkalearning.domain.model.Message
import com.eliasmeireles.kafkalearning.protobuf.MessageProto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MessageMapper {

    fun parse(input: Message): MessageProto.Message

    fun parse(input: MessageProto.Message): Message
}

package com.eliasmeireles.kafkalearning.mapper

import com.eliasmeireles.kafkalearning.model.Message
import com.eliasmeireles.kafkalearning.protobuf.MessageProto
import com.google.protobuf.DynamicMessage
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface MessageMapper {

    fun parse(input: Message): MessageProto.Message

    fun parse(input: MessageProto.Message): Message
}

fun MessageMapper.parse(input: DynamicMessage): Message {
    val proto = MessageProto.Message.parseFrom(input.toByteArray())
    return parse(proto)
}

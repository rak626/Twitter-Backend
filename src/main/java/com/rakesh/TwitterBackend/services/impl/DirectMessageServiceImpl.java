package com.rakesh.TwitterBackend.services.impl;

import com.rakesh.TwitterBackend.entities.DirectMessage;
import com.rakesh.TwitterBackend.entities.User;
import com.rakesh.TwitterBackend.exceptions.ResourceNotFoundException;
import com.rakesh.TwitterBackend.payloads.requests.DirectMessageRequestDto;
import com.rakesh.TwitterBackend.payloads.responses.DirectMessageResponseDto;
import com.rakesh.TwitterBackend.repositories.DirectMessageRepo;
import com.rakesh.TwitterBackend.repositories.UserRepo;
import com.rakesh.TwitterBackend.services.DirectMessageService;
import com.rakesh.TwitterBackend.utils.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectMessageServiceImpl implements DirectMessageService {

    @Autowired
    private DirectMessageRepo directMessageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DtoConverters dtoConverters;

    @Override
    public DirectMessageResponseDto createDirectMessage(DirectMessageRequestDto directMessageRequestDto) {
        User sender = userRepo.findById(directMessageRequestDto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "sender id", directMessageRequestDto.getSenderId()));
        User receiver = userRepo.findById(directMessageRequestDto.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "receiver id", directMessageRequestDto.getReceiverId()));
        DirectMessage directMessage = DirectMessage.builder()
                .content(directMessageRequestDto.getContent())
                .sender(sender)
                .receiver(receiver)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        return dtoConverters.directMessageToDirectMessageResponseDto(directMessage);

    }

    @Override
    public DirectMessageResponseDto updateDirectMessage(DirectMessageRequestDto directMessageRequestDto) {
        User sender = userRepo.findById(directMessageRequestDto.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "sender id", directMessageRequestDto.getSenderId()));
        User receiver = userRepo.findById(directMessageRequestDto.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "receiver id", directMessageRequestDto.getReceiverId()));
        DirectMessage directMessage = DirectMessage.builder()
                .content(directMessageRequestDto.getContent())
                .sender(sender)
                .receiver(receiver)
                .updatedAt(new Date())
                .build();
        return dtoConverters.directMessageToDirectMessageResponseDto(directMessage);
    }

    @Override
    public void deleteDirectMessage(Long directMessageId) {
        DirectMessage directMessage = this.directMessageRepo.findById(directMessageId)
                .orElseThrow(() -> new ResourceNotFoundException("DirectMessage", "direct_message_id", directMessageId));
        directMessageRepo.delete(directMessage);
    }

    @Override
    public List<DirectMessageResponseDto> getAllDirectMessageByReceiver(String receiverId) {
        User user = userRepo.findById(receiverId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "receiver_id", receiverId));
        List<DirectMessage> directMessageList = directMessageRepo.findByReceiver(user);
        List<DirectMessageResponseDto> directMessageResponseDtos = directMessageList.stream()
                .map(directMessage -> dtoConverters.directMessageToDirectMessageResponseDto(directMessage))
                .collect(Collectors.toList());
        return directMessageResponseDtos;
    }

    @Override
    public List<DirectMessageResponseDto> getAllDirectMessageBySender(String senderId) {
        User user = userRepo.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "receiver_id", senderId));
        List<DirectMessage> directMessageList = directMessageRepo.findByReceiver(user);
        List<DirectMessageResponseDto> directMessageResponseDtos = directMessageList.stream()
                .map(directMessage -> dtoConverters.directMessageToDirectMessageResponseDto(directMessage))
                .collect(Collectors.toList());
        return directMessageResponseDtos;
    }

}

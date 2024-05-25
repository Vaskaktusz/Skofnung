package application.controller;

import application.entity.security.Member;
import application.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void save(@RequestBody @Valid Member member) {
        memberService.save(member);
    }
}
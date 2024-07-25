package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.detection.Users;
import com.example.demo.service.detection.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // 사용자 목록을 가져오는 API 엔드포인트
    @GetMapping("/api/users")
    @ResponseBody
    public List<Users> getUsers() {
        return usersService.getAllUsers();
    }

    // 404 페이지로 이동
    @GetMapping("/404")
    public String _404() {
        return "404";
    }

    // 405 페이지로 이동
    @GetMapping("/405")
    public String _405() {
        return "405";
    }

    // 사용자 목록 페이지로 이동
    @GetMapping("/userlist")
    public String userlistForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users currentUser = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 관리자 권한 검증: currentUser가 관리자인지 확인
        if (!currentUser.getUser_privilege().equals("admin")) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근할 수 있는 페이지입니다.");
            return "redirect:/adminerror"; // 관리자가 아니면 관리자 에러 페이지로 리다이렉트
        }

        // 사용자 목록 조회 및 페이지 이동
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    // 사용자 정보 수정 폼으로 이동
    @GetMapping("/modifyuser")
    public String modify() {
        return "modifyuser";
    }
    // 사용자 정보 수정 처리
    @PostMapping("/modifyuser")
    @Transactional
    public String modifyUserSubmit(@RequestBody Users modifiedUser, HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users currentUser = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 관리자 권한 검증: currentUser가 관리자인지 확인
        if (!currentUser.getUser_privilege().equals("admin")) {
            redirectAttributes.addFlashAttribute("error", "관리자만 사용자 정보를 수정할 수 있습니다.");
            return "redirect:/admin"; // 관리자가 아니면 관리자 페이지로 리다이렉트
        }

        // 데이터베이스에서 기존 사용자 정보 가져오기
        Users existingUser = usersService.getUserById(modifiedUser.getUser_id());

        // 사용자가 존재하지 않으면 관리자 페이지로 리다이렉트
        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("error", "수정할 사용자가 존재하지 않습니다.");
            return "redirect:/admin"; // 관리자 페이지로 리다이렉트
        }

        // 비밀번호 변경 여부 확인 및 암호화 처리
        if (!modifiedUser.getPassword().equals(existingUser.getPassword())) {
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = bcryptPasswordEncoder.encode(modifiedUser.getPassword());
            modifiedUser.setPassword(encodedPassword);
        }

     // 사용자 정보 수정 처리
        int updated = usersService.updateUser(modifiedUser);
        if (updated > 0) {
            return "success"; // 클라이언트에 성공 메시지 반환
        } else {
            return "사용자 정보 수정에 실패하였습니다."; // 실패 시 메시지 반환
        }

        
    }


    // 로그인 페이지로 이동
    @GetMapping("/signin")
    public String loginForm() {
        return "signin";
    }

    // 로그인 처리
    @PostMapping("/login")
    @Transactional
    public String login(@RequestParam("user_id") String userId,
                        @RequestParam("password") String password,
                        HttpSession session, RedirectAttributes redirectAttributes) {
        // 데이터베이스에서 사용자 조회
        Users user = usersService.getUserById(userId);

        // 사용자가 존재하지 않으면 로그인 실패 메시지 반환
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "로그인 정보가 잘못되었습니다.");
            return "redirect:/signin"; // 로그인 폼으로 리다이렉트
        }

        // 비밀번호 검증
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bcryptPasswordEncoder.matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 틀렸습니다.");
            return "redirect:/signin"; // 로그인 폼으로 리다이렉트
        }

        // 로그인 성공 시 세션에 사용자 정보 저장
        session.setAttribute("user", user);

        // 대시보드 페이지로 리다이렉트
        return "redirect:/dashboard";
    }

    // 대시보드 페이지로 이동
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보 가져오기
        Users user = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 대시보드 페이지로 이동
        return "dashboard";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    @ResponseBody
    @Transactional
    public String joinSubmit(@RequestBody Users users) {
        // 사용자 입력값 검증
        if (users.getUser_id() == null || users.getUser_id().isEmpty() ||
                users.getPassword() == null || users.getPassword().isEmpty() ||
                users.getUser_privilege() == null || users.getUser_privilege().isEmpty() ||
                users.getUser_name() == null || users.getUser_name().isEmpty()) {
            return "모든 필드를 입력해주세요.";
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bcryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);

        // 회원가입 성공 여부에 따라 처리
        if (usersService.addUser(users) > 0) {
            return "success"; // 회원가입 성공 시 클라이언트에게 success 응답
        } else {
            return "회원가입 실패"; // 회원가입 실패 시 클라이언트에게 실패 메시지 응답
        }
    }

    // 사용자 삭제 처리
    @PostMapping("/deleteuser")
    @Transactional
    public String deleteUser(@RequestParam("user_id") String userId,
                             @RequestParam("password") String password,
                             HttpSession session, RedirectAttributes redirectAttributes) {

        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users currentUser = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 비밀번호 검증
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bcryptPasswordEncoder.matches(password, currentUser.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 틀렸습니다.");
            return "redirect:/deleteuser"; // 비밀번호 재입력 폼으로 리다이렉트
        }

        // 관리자 권한 검증: currentUser가 관리자인지 확인
        if (!currentUser.getUser_privilege().equals("admin")) {
            redirectAttributes.addFlashAttribute("error", "관리자만 사용자를 삭제할 수 있습니다.");
            return "redirect:/admin"; // 관리자가 아니면 관리자 페이지로 리다이렉트
        }

        // 사용자 ID 검증: 데이터베이스에서 해당 사용자 ID가 존재하는지 확인
        Users userToDelete = usersService.getUserById(userId);
        if (userToDelete == null) {
            redirectAttributes.addFlashAttribute("error", "삭제할 사용자가 존재하지 않습니다.");
            return "redirect:/admin"; // 사용자가 존재하지 않으면 관리자 페이지로 리다이렉트
        }

        // 사용자 삭제 처리
        int deleted = usersService.deleteUserById(userId);
        if (deleted > 0) {
            redirectAttributes.addFlashAttribute("success", "사용자 삭제가 완료되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("error", "사용자 삭제에 실패하였습니다.");
        }

        return "redirect:/admin"; // 관리자 페이지로 리다이렉트
    }

    // 사용자 삭제 페이지로 이동
    @GetMapping("/deleteuser")
    public String deleteuser(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users currentUser = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 관리자 권한 검증: currentUser가 관리자인지 확인
        if (!currentUser.getUser_privilege().equals("admin")) {
            redirectAttributes.addFlashAttribute("error", "관리자만 사용자를 삭제할 수 있습니다.");
            return "redirect:/userlist"; // 관리자가 아니면 홈 페이지로 리다이렉트
        }

        // 관리자일 경우 삭제 페이지로 이동
        return "deleteuser";
    }

    // 관리자 페이지로 이동
    @GetMapping("/admin")
    public String admin(HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 현재 로그인한 사용자 정보 가져오기
        Users currentUser = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        }

        // 관리자 권한 검증: currentUser가 관리자인지 확인
        if (!currentUser.getUser_privilege().equals("admin")) {
            redirectAttributes.addFlashAttribute("error", "관리자만 접근할 수 있는 페이지입니다.");
            return "redirect:/adminerror"; // 관리자가 아니면 관리자 에러 페이지로 리다이렉트
        }

        // 관리자 페이지로 이동
        return "admin";
    }

    // 로그 관리/패턴 로그 페이지로 이동
    @GetMapping("/patternlog")
    public String patternlog() {
        return "patternlog";
    }

    // 위험 로그 페이지로 이동
    @GetMapping("/dangerouslog")
    public String dangerouslog() {
        return "dangerouslog";
    }

    
  
    // 관리자가 아닐 경우 관리자 에러 페이지로 이동
    @GetMapping("/adminerror")
    public String adminerror() {
        return "adminerror";
    }
    
    

}

package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.LoginDAO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.MemberRoleDTO;
import com.idle.osmas.member.dto.RoleListDTO;
import com.idle.osmas.member.dto.UserImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{

    private final LoginDAO loginDAO;

    public LoginServiceImpl(LoginDAO loginDAO){
        this.loginDAO = loginDAO;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO member = loginDAO.findMemberById(username);

        if(member == null){
            member = new MemberDTO();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if(member.getMemberRoleList() != null){
            List<RoleListDTO> roleList = member.getMemberRoleList();

            for(int i = 0; i < roleList.size(); i++){
                MemberRoleDTO role = roleList.get(i).getMemberRole();
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        UserImpl user = new UserImpl(member.getId(), member.getPwd(), authorities);
        user.setDetails(member);
        return user;

    }
}

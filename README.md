# PPOS

## Member

#### MemberController
MemberView와 MemberService를 조작합니다. 클라이언트의 요청을 받고 적절한 서비스를 호출하여 비즈니스 로직을 수행한 후, 그 결과를 MemberView에 전달합니다. 

#### MemberService
MemberModel을 매개변수로 받고, 비즈니스 로직을 처리합니다. 데이터베이스나 다른 외부 시스템과의 상호작용을 담당하는 MemberRepository를 호출하여 데이터 액세스를 수행합니다. 

#### MemberModel
비즈니스 객체를 나타냅니다. 데이터를 저장하고 조작하는데 사용됩니다. 

#### MemberDAO(Data Access Object)
데이터베이스나 다른 영구 저장소에 접근하는 데 사용됩니다. 

#### MemberRepository 
패턴에서 데이터 액세스 작업을 추상화하고 캡슐화합니다. 

#### DTO(Data Transfer Object)
데이터 전송 객체입니다. 서비스 계층과 데이터 액세스 계층 간에 데이터를 전송할 때 사용됩니다. 

#### MemberRepository
MemberDAO의 구현체로서, 실제 데이터베이스 조작을 담당합니다. 데이터베이스와의 통신을 수행하고 데이터 액세스 작업을 구현합니다.

#### MemberServiceTest
Junit을 활용하여 MemberService를 테스트합니다.

## MemberPopup

#### MemberPopupController
MemberPopupView와 MemberService를 조작합니다.
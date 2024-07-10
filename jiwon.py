import pygame
import random
import sys

# The rest of your code...

# 게임 초기화
pygame.init()

# 화면 설정
WIDTH, HEIGHT = 800, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("JIWON 슈팅 게임")

# 캐릭터 설정
player_width, player_height = 40, 40  # 주인공의 새로운 크기
player_x, player_y = (WIDTH - player_width) // 2, HEIGHT - player_height - 10
player_speed = 5
original_player_img = pygame.image.load(r'C:\Users\Administrator\Desktop\pygame\protagonist.png').convert_alpha()

# 주인공 이미지 크기 조정
player_img = pygame.transform.scale(original_player_img, (player_width, player_height))

# 총알 설정
bullet_width, bullet_height = 10, 20
bullet_speed = 10
bullets = []

# 운석 설정
meteor_width, meteor_height = 40, 40  # 운석의 새로운 크기
meteor_speed = 3
meteors = []
meteor_spawn_delay = 1000  # milliseconds
last_meteor_spawn = pygame.time.get_ticks()
original_meteor_img = pygame.image.load(r'C:\Users\Administrator\Desktop\pygame\meteor.png').convert_alpha()
meteor_img = pygame.transform.rotate(original_meteor_img, 90)  # 90도 회전

# 운석 이미지 크기 조정
meteor_img = pygame.transform.scale(meteor_img, (meteor_width, meteor_height))

# 배경 설정
background_img = pygame.image.load(r'C:\Users\Administrator\Desktop\pygame\background.jpg').convert()

# 점수와 플레이어 체력 설정
score = 0
player_hp = 5

# 시간
clock = pygame.time.Clock()

# 게임 종료 여부
game_over = False

# 게임 시작 여부
game_started = False

# 게임 시작 안내 메시지 표시 설정
font_start = pygame.font.Font(None, 36)
start_text = font_start.render("Press A to Start", True, (255, 255, 255))
start_text_rect = start_text.get_rect(center=(WIDTH // 2, HEIGHT // 2))

# 게임 루프
while not game_over:
    screen.blit(background_img, (0, 0))  # 배경 그리기

    # 게임 시작 안내 메시지 표시
    if not game_started:
        screen.blit(start_text, start_text_rect)

    # 이벤트 처리
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_a:  # "A" 키를 누르면 게임 시작
                game_started = True
            elif event.key == pygame.K_r and game_over:  # "R" 키를 누르면 재시작
                # 초기화
                bullets.clear()
                meteors.clear()
                score = 0
                player_hp = 5
                player_x, player_y = (WIDTH - player_width) // 2, HEIGHT - player_height - 10
                game_over = False
            elif event.key == pygame.K_SPACE and game_started:  # "SPACE" 키를 누르면 공격
                bullets.append(pygame.Rect(player_x + player_width // 2 - bullet_width // 2,
                                           player_y - bullet_height, bullet_width, bullet_height))

    # 게임이 시작되었을 때만 게임 루프 실행
    if game_started:
        # 캐릭터 이동
        keys = pygame.key.get_pressed()
        if keys[pygame.K_LEFT] and player_x > 0:
            player_x -= player_speed
        if keys[pygame.K_RIGHT] and player_x < WIDTH - player_width:
            player_x += player_speed

        # 총알 이동
        for bullet in bullets[:]:
            bullet.y -= bullet_speed
            if bullet.y < 0:
                bullets.remove(bullet)

        # 운석 생성
        now = pygame.time.get_ticks()
        if now - last_meteor_spawn > meteor_spawn_delay:
            meteor_x = random.randint(0, WIDTH - meteor_width)
            meteors.append(pygame.Rect(meteor_x, -meteor_height, meteor_width, meteor_height))
            last_meteor_spawn = now

        # 운석 이동
        for meteor in meteors[:]:
            meteor.y += meteor_speed
            if meteor.y > HEIGHT:
                meteors.remove(meteor)

        # 충돌 검사
        for bullet in bullets[:]:
            for meteor in meteors[:]:
                if bullet.colliderect(meteor):
                    bullets.remove(bullet)
                    meteors.remove(meteor)
                    score += 1

        # 운석에 부딪힘
        for meteor in meteors:
            if meteor.colliderect(pygame.Rect(player_x, player_y, player_width, player_height)):
                player_hp -= 1

        # 그리기
        screen.blit(player_img, (player_x, player_y))  # 주인공 그리기
        for bullet in bullets:
            pygame.draw.rect(screen, (255, 0, 0), bullet)
        for meteor in meteors:
            screen.blit(meteor_img, (meteor.x, meteor.y))  # 운석 그리기

        # 점수와 체력 출력
        font = pygame.font.Font(None, 36)
        score_text = font.render(f"Score: {score}", True, (255, 255, 255))
        screen.blit(score_text, (10, 10))
        health_text = font.render(f"HP: {player_hp}", True, (255, 255, 255))
        screen.blit(health_text, (10, 50))

        # 게임 종료 조건
        if score >= 10:
            font = pygame.font.Font(None, 100)
            text = font.render("Clear", True, (255, 255, 255))
            screen.blit(text, (WIDTH // 2 - text.get_width() // 2, HEIGHT // 2 - text.get_height() // 2))
            pygame.display.flip()
            pygame.time.delay(2000)  # 2초 대기
            game_over = True
        elif player_hp <= 0:
            font = pygame.font.Font(None, 100)
            text = font.render("Fail", True, (255, 255, 255))
            screen.blit(text, (WIDTH // 2 - text.get_width() // 2, HEIGHT // 2 - text.get_height() // 2))
            pygame.display.flip()
            pygame.time.delay(2000)  # 2초 대기
            game_over = True

    pygame.display.flip()
    clock.tick(60)

pygame.quit()

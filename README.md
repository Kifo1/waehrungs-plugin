import pygame
import sys
import random

# Initialisiere Pygame
pygame.init()

# Setze Fenstergröße
SCREEN_WIDTH, SCREEN_HEIGHT = 400, 700
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))

# Setze Titel des Fensters
pygame.display.set_caption('Flappy Bird')

# Lade Bilder
bird_images = [pygame.image.load('bird1.png'), pygame.image.load('bird2.png'), pygame.image.load('bird3.png')]

# Röhrenbilder
pipe_image = pygame.image.load('pipe.png')

# Hintergrundbild
bg_image = pygame.image.load('bg.png')

# Lade Schriftarten
font = pygame.font.Font('8bit.ttf', 30)

# Spielvariablen
GRAVITY = 0.25
FLAP_STRENGTH = 6

# Klasse für den Vogel
class Bird:
    def __init__(self):
        self.x = SCREEN_WIDTH // 2
        self.y = SCREEN_HEIGHT // 2
        self.velocity = 0
        self.flap_count = 0
        self.alive = True
        self.image = bird_images[0]

    def update(self):
        # Fliege nach unten, wenn kein Klick
        self.velocity += GRAVITY
        self.y += self.velocity

        # Klick erkennen
        keys = pygame.key.get_pressed()
        if keys[pygame.K_SPACE]:
            self.velocity = -FLAP_STRENGTH

        # Animation für Flügelschlag
        self.flap_count += 1
        if self.flap_count < 5:
            self.image = bird_images[0]
        elif self.flap_count < 10:
            self.image = bird_images[1]
        else:
            self.flap_count = 0
            self.image = bird_images[2]

        # Prüfe Kollision mit dem Boden
        if self.y > SCREEN_HEIGHT:
            self.alive = False

    def draw(self):
        screen.blit(self.image, (self.x - self.image.get_width() // 2, self.y - self.image.get_height() // 2))

# Klasse für Röhre
class Pipe:
    def __init__(self):
        self.x = SCREEN_WIDTH
        self.y = random.randint(0, SCREEN_HEIGHT // 2)
        self.alive = True

    def update(self):
        self.x -= 2
        if self.x < -50:
            self.alive = False

    def draw(self):
        screen.blit(pipe_image, (
